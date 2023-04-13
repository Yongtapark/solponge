package com.example.demo.controller;

import com.example.demo.domain.cart.Cart;
import com.example.demo.domain.cart.CartItem;
import com.example.demo.domain.member.Member;
import com.example.demo.domain.member.login.session.SessionConst;
import com.example.demo.service.interfaces.CartService;
import com.example.demo.service.interfaces.MemberService;
import com.example.demo.service.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Controller
@Slf4j
@RequiredArgsConstructor

@RequestMapping("/com.solponge/member/{memberNum}")
public class OrderController {
    private final ProductService productService;
    private final MemberService memberService;
    private final CartService cartService;



    @PostMapping("/payments")
    public String processPayments(@ModelAttribute("cart")Cart cart, Model model) {
        // 체크박스가 선택된 상품만 걸러내기
        log.info("cart={}",cart);

        List<CartItem> selectedItems = cart.getCartItems().stream()
                .filter(CartItem::isSelected)
                .collect(Collectors.toList());
        log.info("selectedItems={}",selectedItems);


        return "redirect:/success";
    }





    private Member getLoginMember(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null ? (Member) session.getAttribute(SessionConst.LOGIN_MEMBER) : null;
    }

}
