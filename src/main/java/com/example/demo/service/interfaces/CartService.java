package com.example.demo.service.interfaces;

import com.example.demo.domain.cart.Cart;
import com.example.demo.domain.cart.CartItem;


import java.util.List;

public interface CartService {
    /**
     * cart_item
     * 상품 추가, 수정, 삭제, 확인
     */
    CartItem addItem(CartItem cartItem);

    void updateItem(Long cart_item_num, CartItem cartItem);

    void deleteItem(CartItem cartItem);

    CartItem findItem(Long cartItemNum);

    /**
     * cart
     * 상품 리스트 확인
     */
    Cart createCart(Cart cart);

    Cart getMyCart(Long memberNo);


    /**
     * 서비스
     */
    void deleteCartItemByMember(Long MEMBER_NO);

    void deleteCartByMember(Long MEMBER_NUM);

   // List<CartList> showMyCart(int MEMBER_NUM);

}