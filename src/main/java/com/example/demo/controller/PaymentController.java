package com.example.demo.controller;

import com.example.demo.domain.cart.Cart;
import com.example.demo.domain.cart.CartItem;
import com.example.demo.domain.member.Member;
import com.example.demo.domain.member.MemberJoinForm;
import com.example.demo.domain.member.login.session.SessionConst;
import com.example.demo.domain.payment.OrderList;
import com.example.demo.domain.payment.PayForm;
import com.example.demo.domain.payment.Payment;
import com.example.demo.domain.product.Product;
import com.example.demo.service.interfaces.CartService;
import com.example.demo.service.interfaces.MemberService;
import com.example.demo.service.interfaces.PaymentService;
import com.example.demo.service.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    private final PaymentService paymentService;
    private final MemberService memberService;
    private final CartService cartService;



    @PostMapping("/payments")
    public String processPayments(@ModelAttribute("cart")Cart cart, Model model,HttpServletRequest request) {
        getLoginMember(request);

        log.info("cart={}",cart);
        List<CartItem> selectedItems = cart.getCartItems().stream()// 체크박스가 선택된 상품만 걸러내기
                .filter(CartItem::isSelected)
                .collect(Collectors.toList());
        log.info("selectedItems={}",selectedItems);

        model.addAttribute("selectedItems",selectedItems);//다음 페이지로 보내기위해 model 에 전송
        return "payment/payments";
    }

    @PostMapping("/payments/pay")
    public String payinfo(@ModelAttribute("payForm") PayForm payForm,
                          Model model, HttpServletRequest request){
        Member loginMember = getLoginMember(request);
        combineString(payForm);
        log.info("payForm={}",payForm);
        return "payment/pay";
    }

    @GetMapping(value = "/payments/success")
    public String successmove(HttpServletRequest request,
                              Model model) {
        Member loginMember = getLoginMember(request);
        model.addAttribute("memberNum",loginMember.getMemberNum());
        return "payment/success";
    }

    @GetMapping(value = "/payments/fail")
    public String failmove(HttpServletRequest request,
                           Model model) {
        Member loginMember = getLoginMember(request);
        model.addAttribute("member_No",loginMember.getMemberNum());
        return "payment/fail";
    }

    @PostMapping(value = "/payments/su")
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void insertPayment(@RequestBody PayForm payForm, HttpServletRequest request) {
        log.info("PayForm={}",payForm);
        getLoginMember(request);

        List<OrderList> orderList = payForm.getOrderList();
        for (OrderList list : orderList) {
            Product product = productService.findByNo(list.getProductNum()).get();
            Member member = memberService.findByNo(payForm.getMemberNum()).get();
            CartItem item = cartService.findItem(list.getCartItemNum());
            if(product.getProductStock()-list.getPaymentStock()<0){
                throw new RuntimeException("상품 재고가 부족합니다.");
            }else {
                paymentService.subtractStock(list.getProductNum(),list.getPaymentStock());
            }
            Payment payment = new Payment(member,
                    product,
                    list.getPaymentStock(),
                    payForm.getMemberPhone(),
                    payForm.getMemberEmail(),
                    payForm.getMemberAddress(),
                    payForm.getDeliveryInfo());

            Long save = paymentService.save(payment);
            log.info("saveNum={}",save);
            cartService.deleteItem(item);

        }

    }






    /*문자열 합치기*/
    private static void combineString(PayForm payForm) {
        String address = payForm.getMemberAddress1() + "/" + payForm.getMemberAddress2() + "/" + payForm.getMemberAddress3();
        payForm.setMemberAddress(address);

        String email = payForm.getMemberEmail1() +"@"+ payForm.getMemberEmail2();
        payForm.setMemberEmail(email);

        String phone = payForm.getMemberPhone1() + "-" + payForm.getMemberPhone2() + "-" + payForm.getMemberPhone3();
        payForm.setMemberPhone(phone);
    }

    private Member getLoginMember(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null ? (Member) session.getAttribute(SessionConst.LOGIN_MEMBER) : null;
    }

}
