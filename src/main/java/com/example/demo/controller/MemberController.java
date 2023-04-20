package com.example.demo.controller;

import com.example.demo.domain.member.Member;
import com.example.demo.domain.member.MemberJoinForm;
import com.example.demo.domain.member.login.session.SessionConst;
import com.example.demo.domain.payment.Payment;
import com.example.demo.domain.product.Product;
import com.example.demo.service.interfaces.MemberService;
import com.example.demo.service.interfaces.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/com.solponge/member/{memberNum}")
public class MemberController {
    private final MemberService memberService;
    private final PaymentService paymentService;

    @GetMapping("/myPage")
    public String getMyPage(Model model,
                            @ModelAttribute MemberJoinForm updateMember,
                            HttpServletRequest request) {
        Member loginMember = getLoginMember(request);
        model.addAttribute("member", loginMember);
        return "member/updateForm";
    }

    @PostMapping("/myPage")
    public String updateMember(HttpSession Session,
                               @ModelAttribute MemberJoinForm updateMember,
                               BindingResult bindingResult,
                               HttpServletRequest request,
                               Model model) {
        Member loginMember = getLoginMember(request);
        model.addAttribute("member",loginMember);
        if(!updateMember.getMemberPwd().equals(updateMember.getMemberPwdCheck())){
            bindingResult.rejectValue("memberPwdCheck","pwdCheckFail","비밀번호가 일치하지 않습니다.");
            log.info("join.bindingResult={}",bindingResult);
        }
        log.info("member={}",updateMember);
        if(bindingResult.hasErrors()){
            return "member/updateForm";
        }
        //회원 정보 업데이트
        updateMember(loginMember,
                updateMember.getMemberPwd(),
                updateMember.getMemberEmail1(),
                updateMember.getMemberEmail2(),
                updateMember.getMemberAddress1(),
                updateMember.getMemberAddress2(),
                updateMember.getMemberAddress3(),
                updateMember.getMemberPhone1(),
                updateMember.getMemberPhone2(),
                updateMember.getMemberPhone3());
        //업데이트한 회원정보를 세션에 저장
        sessionSave(loginMember, Session);
        return "redirect:/com.solponge/main";
    }

    @GetMapping("/myPage/delete")
    public String deleteMember(HttpServletRequest request) {
        Member loginMember = getLoginMember(request);

        memberService.delete(loginMember.getMemberNum());//멤버삭제

        HttpSession session = request.getSession(false);
        if (session!=null){
            session.invalidate();
        }
        return "redirect:/com.solponge/main";
    }

    @GetMapping("/paymentList")
    public String produtslist(Model model, HttpServletRequest request){
        Member loginMember = getLoginMember(request);
        List<Payment> paymentList = paymentService.getPaymentList(loginMember.getMemberNum());
        for (Payment payment : paymentList) {
            Product product = payment.getProduct();
            Long paymentStock = payment.getPaymentStock();
            model.addAttribute("product",product);

        }
        model.addAttribute("paymentList",paymentList);

        return "member/paymentList";
    }

    /**
     * 메서드
     */

    /*회원 정보 수정 시 해당 정보 세션에 저장*/
    private void sessionSave(Member loginMember, HttpSession Session) {
        // 업데이트된 멤버 객체 찾기
        Member updateMemeber = memberService.findByNo(loginMember.getMemberNum()).get();
        log.info("updatedMember={}",updateMemeber);
        //세션에 업데이트된 찾은 회원 정보 저장
        Session.setAttribute(SessionConst.LOGIN_MEMBER,updateMemeber);
    }

    /*문자열 합치기*/
    private void updateMember(Member loginMember, String member_pwd, String email1, String email2, String sample6_postcode, String sample6_address, String sample6_detailAddress, String firstnum, String secnum, String thrnum) {
        String email = email1 + "@" + email2;
        String address = sample6_postcode + "/" + sample6_address + "/" + sample6_detailAddress;
        String phone = firstnum + "-" + secnum + "-" + thrnum;
        //멤버 수정정보 생성
        Member Member = new Member(member_pwd,address,email,phone);
        //멤버 업데이트
        memberService.update(loginMember.getMemberNum(),Member);
    }

    /*회원정보 받음*/
    private Member getLoginMember(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null ? (Member) session.getAttribute(SessionConst.LOGIN_MEMBER) : null;
    }
}

