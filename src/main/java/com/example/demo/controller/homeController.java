package com.example.demo.controller;

import com.example.demo.domain.cart.Cart;
import com.example.demo.domain.member.Member;
import com.example.demo.domain.member.MemberJoinForm;
import com.example.demo.domain.member.login.session.SessionConst;
import com.example.demo.service.interfaces.CartService;
import com.example.demo.service.interfaces.MemberService;
import com.example.demo.service.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.example.demo.domain.cart.QCart.cart;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/com.solponge")
public class homeController {
    private final MemberService memberService;
    private final ProductService productService;
    private final CartService cartService;

    @GetMapping("/main")
    public String home(Model model, HttpServletRequest request){
        Member loginMember = getLoginMember(request);
        model.addAttribute("newTop8List", productService.newTop8List());
        model.addAttribute("popTop8List", productService.popTop8List());
        //model.addAttribute("getJopInfoNewTop8List", jopinfoService.getJopInfoNewTop8List());
        model.addAttribute("loginMember",loginMember);
       /* try{
            Long userNo = loginMember.getMEMBER_NO();
            System.out.println(userNo);
            String id = loginMember.getMEMBER_ID();
            System.out.println(id);
            if(id !=null) {
                new responseScrap(model, userNo, jobscrapService, "JobScrap", "JobScrap2");
            }
        }catch (Exception e){
            System.out.println("오류발생");
        }*/
        return "main";
    }

    @GetMapping("/join")
    public String getJoin(@ModelAttribute("member") MemberJoinForm member){
        log.info("==getJoin==");
        return "member/signup";
    }
    @PostMapping("/join")
    public String postJoin(@Validated @ModelAttribute("member") MemberJoinForm member, BindingResult bindingResult){
        log.info("==postJoin==");
        List<Member> all = memberService.findAll();
        for (Member members : all) {
            String memberId = members.getMemberId();
            if(member.getMemberId().equals(memberId)){
                bindingResult.rejectValue("memberId","idCheckFail","이미 존재하는 회원입니다.");
            }
        }

        if(!member.getMemberPwd().equals(member.getMemberPwdCheck())){
            bindingResult.rejectValue("memberPwdCheck","pwdCheckFail","비밀번호가 일치하지 않습니다.");
            log.info("join.bindingResult={}",bindingResult);
        }
        log.info("member={}",member);
        if(bindingResult.hasErrors()){
            return "member/signup";
        }

        combineString(member); //문자열 합치기 주소,이메일,폰


        Member joindMember = new Member();
        joindMember.setMemberId(member.getMemberId());
        joindMember.setMemberPwd(member.getMemberPwd());
        joindMember.setMemberName(member.getMemberName());
        joindMember.setMemberEmail(member.getMemberEmail());
        joindMember.setMemberAddress(member.getMemberAddress());
        joindMember.setMemberPhone(member.getMemberPhone());
        Long join = memberService.join(joindMember);
        log.info("join={}",join);
        log.info("joinedMember={}",join);
        //회원가입 시 카트 생성
        Cart cart = cartService.createCart(new Cart(joindMember));
        log.info("cartCreated={}",cart);
        return "member/addComplete";
    }


    /**
     * 메서드
     */

    /*회원가입 시 받은 문자열 합치기*/
    private static void combineString(MemberJoinForm member) {
        String address = member.getMemberAddress1() + "/" + member.getMemberAddress2() + "/" + member.getMemberAddress3();
        member.setMemberAddress(address);

        String email = member.getMemberEmail1() +"@"+ member.getMemberEmail2();
        member.setMemberEmail(email);

        String phone = member.getMemberPhone1() + "-" + member.getMemberPhone2() + "-" + member.getMemberPhone3();
        member.setMemberPhone(phone);
    }

    //세션 로그인 정보 받기
    private Member getLoginMember(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null ? (Member) session.getAttribute(SessionConst.LOGIN_MEMBER) : null;
    }

}

