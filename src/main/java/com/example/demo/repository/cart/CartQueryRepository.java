package com.example.demo.repository.cart;

import com.example.demo.domain.cart.CartList;
import com.example.demo.domain.cart.QCart;
import com.example.demo.domain.cart.QCartItem;
import com.example.demo.domain.cart.QCartList;
import com.example.demo.domain.product.QProduct;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.example.demo.domain.cart.QCart.cart;
import static com.example.demo.domain.cart.QCartItem.cartItem;
import static com.example.demo.domain.cart.QCartList.cartList;
import static com.example.demo.domain.product.QProduct.product;

public class CartQueryRepository {
    private final JPAQueryFactory query;
    private final CartRepository cartRepository;

    public CartQueryRepository(EntityManager em, CartRepository cartRepository) {
        this.query = new JPAQueryFactory(em);
        this.cartRepository = cartRepository;
    }

    public List<CartList> showMyCart(int memberNum) {
        return query.select(cartList)
                .from(cartList)
                .join(cart).on(cart.cartNum.eq(cartList.cartNum))
                .join(cart.cartItems, cartItem).fetchJoin()
                .where(cart.member.memberNo.eq(Long.valueOf(memberNum)))
                .fetch();
    }




}
