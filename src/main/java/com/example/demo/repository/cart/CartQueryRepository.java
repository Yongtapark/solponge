package com.example.demo.repository.cart;

import com.example.demo.domain.cart.Cart;
import com.example.demo.domain.cart.CartItem;
import com.example.demo.domain.cart.QCart;
import com.example.demo.utils.SearchCond;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.demo.domain.cart.QCartItem.cartItem;
import static com.example.demo.domain.member.QMember.member;


public class CartQueryRepository {
    private final JPAQueryFactory query;

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;

    public CartQueryRepository(EntityManager em, CartItemRepository cartItemRepository, CartRepository cartRepository) {
        this.query = new JPAQueryFactory(em);
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
    }

    public Cart findByMemberNum(Long memberNum) {
        Cart cart = query.selectFrom(QCart.cart)
                .where(QCart.cart.member.memberNum.eq(memberNum))
                .fetchOne();

            // cart의 cartItems 목록에서 각 cartItem에 대해 isDeleted가 false인 것만 필터링
            List<CartItem> filteredCartItems = cart.getCartItems().stream()
                    .filter(cartItem -> !cartItem.getIsDeleted())
                    .collect(Collectors.toList());

            // 필터링된 cartItems 목록으로 cart의 cartItems를 설정
            cart.setCartItems(filteredCartItems);

        return cart;
    }



    private List<CartItem> paginateCartItems(List<CartItem> cartItems, Pageable pageable){
        int start = (int)pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), cartItems.size());
        return cartItems.subList(start,end);
    }
    private List<CartItem> searchCartItems(SearchCond cond){
        return query.selectFrom(cartItem)
                .where(cartItem.isDeleted.eq(Boolean.FALSE).and(searchBySelect((cond.getSearchSelect()), cond.getSearchValue())))
                .fetch();
    }


    public Page<CartItem> search(SearchCond cond, Pageable pageable) {
        List<CartItem> searchCartItems = searchCartItems(cond);
        List<CartItem> paginateCartItems = paginateCartItems(searchCartItems, pageable);
        return new PageImpl<>(paginateCartItems, pageable, searchCartItems.size());
    }


    private BooleanExpression searchBySelect(String searchSelect, String searchValue){
        if(StringUtils.hasText(searchSelect)&& StringUtils.hasText(searchValue)){
            switch (searchSelect){
                case "all":
                    return cartItem.product.productTitle.contains(searchValue)
                            .or(cartItem.cartItemStock.stringValue().contains(searchValue));
                case "productTitle":
                    return member.memberId.contains(searchValue);
                case"cartItemStock":
                    return cartItem.cartItemStock.stringValue().contains(searchValue);
            }
        }
        return null;
    }

    public void deleteLogical(Long cartItemNum){
        query.update(cartItem)
                .set(cartItem.isDeleted,Boolean.TRUE)
                .where(cartItem.cartItemNum.eq(cartItemNum))
                .execute();
    }

}
