package com.example.demo.controller;

import com.example.demo.domain.cart.Cart;
import com.example.demo.domain.member.Member;
import com.example.demo.service.interfaces.CartService;
import com.example.demo.service.interfaces.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
@RequiredArgsConstructor
@Transactional
class homeControllerTest {
    @Autowired
    MemberService memberService;
    @Autowired
    CartService cartService;

    @Test
    void getJoin() {
        //given
        Member member = new Member("id","pwd","name","address","email","phone");
        //when
        Long join = memberService.join(member);

        Cart cart = new Cart(member);
        log.info("cartNum={}",cart.getCartNum());
        log.info("cartDate={}",cart.getCartDate());
        log.info("cartMember={}",cart.getMember().getMemberId());

        //then
        Cart createdCart = cartService.createCart(cart);
        Assertions.assertThat(cart).isEqualTo(createdCart);

    }

    @Test
    void postJoin() {

    }
}