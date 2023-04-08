package com.example.demo.service.interfaces;

import com.example.demo.domain.cart.Cart;
import com.example.demo.domain.cart.CartItem;
import com.example.demo.domain.cart.CartList;

import java.util.List;

public interface CartService {
    /**
     * cart_item
     * 상품 추가, 수정, 삭제, 확인
     */
    int addItem(CartItem cartItem);
    void updateItem(int cart_item_num, CartItem cartItem);
    void deleteItem(int cartItem_num);
    CartItem findItem(int cart_item_num);

    /**
     * cart
     * 상품 리스트 확인
     */
    int createCart(Cart cartVo);

    Cart getMyCart(int member_num);

    List<CartList> cartList(int member_num);

    /**
     * 서비스
     */
    void deleteCartItemByMember(int MEMBER_NO);
    void deleteCartByMember(int MEMBER_NUM);
}
