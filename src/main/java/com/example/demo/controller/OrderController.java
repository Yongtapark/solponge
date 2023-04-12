package com.example.demo.controller;

import com.example.demo.domain.member.Member;
import com.example.demo.domain.member.login.session.SessionConst;
import com.example.demo.service.interfaces.CartService;
import com.example.demo.service.interfaces.MemberService;
import com.example.demo.service.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@Slf4j
@RequiredArgsConstructor

@RequestMapping("/com.solponge/member/{MEMBER_NO}")
public class OrderController {
    private final ProductService productService;
    private final MemberService memberService;
    private final CartService cartService;



    @PostMapping("/payments")
    public String postItem(HttpServletRequest request, @RequestParam("cartItems") List<String> cartItems, @RequestParam(value = "order", required = false) List<String> orderProductNums,
                           Model model) {

        Member loginMember = getLoginMember(request);//사용자 확인

        List<String> cartItems1 = cartItems;
        log.info("cartItems1={}",cartItems1);
return null;
    }




    private Member getLoginMember(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null ? (Member) session.getAttribute(SessionConst.LOGIN_MEMBER) : null;
    }

}
