package com.example.demo.service;


import com.example.demo.domain.cart.Cart;
import com.example.demo.domain.cart.CartItem;
import com.example.demo.repository.cart.CartItemRepository;

import com.example.demo.repository.cart.CartQueryRepository;
import com.example.demo.repository.cart.CartRepository;
import com.example.demo.service.interfaces.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public CartItem addItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }


    @Override
    public void deleteItem(Long cartItemNum) {
        cartQueryRepository.deleteLogical(cartItemNum);
    }

    @Override
    public CartItem findItem(Long cartItemNum) {
       return cartItemRepository.findById(cartItemNum).get();
    }

    @Override
    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Cart getMyCart(Long memberNum) {
        return cartQueryRepository.findByMemberNum(memberNum);
    }

    @Override
    public void deleteCartByMemberNum(Long memberNum) {
        cartRepository.deleteByMemberMemberNum(memberNum);
    }

}
