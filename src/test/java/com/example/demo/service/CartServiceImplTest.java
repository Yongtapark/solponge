package com.example.demo.service;

import com.example.demo.domain.cart.Cart;
import com.example.demo.repository.cart.CartRepository;
import com.example.demo.service.interfaces.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@RequiredArgsConstructor
@Transactional
class CartServiceImplTest {

    @Autowired
    CartService cartService;
    @Autowired
    CartRepository cartRepository;
    @Test
    void getMyCart() {
        Cart cart = cartRepository.findById(3L).get();
        log.info("cart={}",cart.getMember().getMemberName());
        Cart cart1 = cartRepository.findByMemberMemberNum(3L).get();
        log.info("cart1={}",cart1.getMember().getMemberName());
    }
}