package com.example.demo.service;


import com.example.demo.domain.cart.Cart;
import com.example.demo.domain.cart.CartItem;
import com.example.demo.domain.cart.CartList;
import com.example.demo.repository.cart.CartItemRepository;
import com.example.demo.repository.cart.CartQueryRepository;
import com.example.demo.repository.cart.CartRepository;
import com.example.demo.service.interfaces.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class CartServiceImpl implements CartService {
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final CartQueryRepository cartQueryRepository;

    public CartServiceImpl(CartItemRepository cartItemRepository, CartRepository repository, CartQueryRepository cartQueryRepository) {
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = repository;
        this.cartQueryRepository = cartQueryRepository;
    }

    @Override
    public int addItem(CartItem cartItem) {
        return Math.toIntExact(cartItemRepository.save(cartItem).getCartItemNum());
    }

    @Override
    public void updateItem(int cart_item_num, CartItem cartItem) {
        CartItem item = findItem(cart_item_num);

        item.setCartItemNum(cartItem.getCartItemNum());
        item.setMember(cartItem.getMember());
        item.setProduct(cartItem.getProduct());
        item.setCartItemStock(cartItem.getCartItemStock());
    }

    @Override
    public void deleteItem(int cartItem_num) {
        CartItem item = findItem(cartItem_num);
        cartItemRepository.delete(item);
    }

    @Override
    public CartItem findItem(int cart_item_num) {
       return cartItemRepository.findById((long) cart_item_num).get();
    }

    @Override
    public int createCart(Cart cart) {
        return cartRepository.save(cart).getCartNum();
    }

    @Override
    public Cart getMyCart(int member_num) {
        return cartRepository.findById((long) member_num).get();
    }

    @Override
    public List<CartList> cartList(int member_num) {
        return cartQueryRepository.showMyCart(member_num);
    }

    @Override
    public void deleteCartItemByMember(int MEMBER_NO) {
        CartItem item = findItem(MEMBER_NO);
        cartItemRepository.delete(item);
    }

    @Override
    public void deleteCartByMember(int MEMBER_NUM) {
        Cart myCart = getMyCart(MEMBER_NUM);
        cartRepository.delete(myCart);
    }
}
