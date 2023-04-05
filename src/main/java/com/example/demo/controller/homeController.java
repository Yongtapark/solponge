package com.example.demo.controller;

import com.example.demo.domain.member.Member;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/com.solponge")
public class homeController {
    private final MemberService memberService;

    @GetMapping("/join")
    String getJoin(@ModelAttribute("member") Member member){
        return "member/signup";
    }
    @PostMapping("/join")
    public String postJoin(@Validated @ModelAttribute("member") Member member, BindingResult bindingResult){
        List<Member> all = memberService.findAll();
        for (Member members : all) {
            String memberId = members.getMemberId();
            if(member.getMemberId().equals(memberId)){
                bindingResult.rejectValue("MEMBER_ID","idCheckFail","이미 존재하는 회원입니다.");
            }
        }

        if(!member.getMemberPwd().equals(member.getMemberPwdCheck())){
            bindingResult.rejectValue("MEMBER_PWD_CHECK","pwdCheckFail","비밀번호가 일치하지 않습니다.");
            log.info("join.bindingResult={}",bindingResult);
        }
        log.info("member={}",member);
        if(bindingResult.hasErrors()){
            return "member/signup";
        }

        combineString(member); //문자열 합치기 주소,이메일,폰
        Long join = memberService.join(member);
       /* log.info("joinedMember={}",join);
        //회원가입 시 카트 생성
        int cart = cartService.createCart(new CartVo(Math.toIntExact(join)));
        log.info("cartCreated={}",cart);*/
        return "member/addComplete";
    }


    /**
     * 메서드
     */

    /*회원가입 시 받은 문자열 합치기*/
    private static void combineString(Member member) {
        String address = member.getMemberAddress1() + "/" + member.getMemberAddress2() + "/" + member.getMemberAddress3();
        member.setMemberAddress(address);

        String email = member.getMemberEmail1() +"@"+ member.getMemberEmail2();
        member.setMemberEmail(email);

        String phone = member.getMemberPhone1() + "-" + member.getMemberPhone2() + "-" + member.getMemberPhone3();
        member.setMemberPwd(phone);
    }

   /* *//*세션 로그인 정보 받기*//*
    private Member getLoginMember(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null ? (Member) session.getAttribute(SessionConst.LOGIN_MEMBER) : null;
    }*/

}

