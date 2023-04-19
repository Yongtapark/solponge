package com.example.demo.controller;

import com.example.demo.domain.cart.Cart;
import com.example.demo.domain.cart.CartItem;
import com.example.demo.domain.member.Member;
import com.example.demo.domain.member.MemberJoinForm;
import com.example.demo.domain.member.login.session.SessionConst;
import com.example.demo.domain.payment.PayForm;
import com.example.demo.service.interfaces.CartService;
import com.example.demo.service.interfaces.MemberService;
import com.example.demo.service.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@Slf4j
@RequiredArgsConstructor

@RequestMapping("/com.solponge/member/{memberNum}")
public class PaymentController {
    private final ProductService productService;
    private final MemberService memberService;
    private final CartService cartService;



    @PostMapping("/payments")
    public String processPayments(@ModelAttribute("cart")Cart cart, Model model,HttpServletRequest request) {
        getLoginMember(request);
        // 체크박스가 선택된 상품만 걸러내기
        log.info("cart={}",cart);

        List<CartItem> selectedItems = cart.getCartItems().stream()
                .filter(CartItem::isSelected)
                .collect(Collectors.toList());
        log.info("selectedItems={}",selectedItems);
        model.addAttribute("selectedItems",selectedItems);
        return "payment/payments";
    }

    @PostMapping("/payments/pay")
    public String payinfo(@ModelAttribute("loginMember") PayForm payForm,
                          Model model, HttpServletRequest request){
        Member loginMember = getLoginMember(request);

        combineString(payForm);


        log.info("payForm={}",payForm);

        String[] product_num = request.getParameterValues("product_num");
        String[] payment_stock = request.getParameterValues("payment_stock");
        String[] cartItem_num = request.getParameterValues("cartItem_num");
       /* for (int i = 0; i < product_num.length; i++){
            int product_stock = productService.getproduct(Integer.parseInt(product_num[i])).getProduct_stock();
            try{
                if(product_stock - Integer.parseInt(payment_stock[i]) < 0){
                    throw new OutOfStockException("상품의 재고가 부족합니다.");
                }
            }catch (Exception e){
                return "product/stockfail";
            }
        }*/
       /* model.addAttribute("member_No", loginMember.getMemberNum());
        model.addAttribute("payment_num", payment_num);
        model.addAttribute("total_price", total_price);
        model.addAttribute("address", payForm.getMemberAddress());
        model.addAttribute("phone", payForm.getMemberPhone());
        model.addAttribute("email", loginMember.getMemberEmail());
        model.addAttribute("m_name", payForm.getMemberName());
        model.addAttribute("delivery_info", delivery_info);
        model.addAttribute("product_num", product_num);
        model.addAttribute("payment_stock", payment_stock);
        model.addAttribute("cartItem_num", cartItem_num);*/
        return "product/pay";
    }





    /*문자열 합치기*/
    private static void combineString(PayForm payForm) {
        String address = payForm.getMemberAddress1() + "/" + payForm.getMemberAddress2() + "/" + payForm.getMemberAddress3();
        payForm.setMemberAddress(address);

        String phone = payForm.getMemberPhone1() + "-" + payForm.getMemberPhone2() + "-" + payForm.getMemberPhone3();
        payForm.setMemberPhone(phone);
    }

    private Member getLoginMember(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null ? (Member) session.getAttribute(SessionConst.LOGIN_MEMBER) : null;
    }

}
