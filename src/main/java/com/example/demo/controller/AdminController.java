package com.example.demo.controller;

import com.example.demo.domain.member.Member;
import com.example.demo.domain.member.MemberJoinForm;
import com.example.demo.domain.member.login.session.SessionConst;
import com.example.demo.service.interfaces.CartService;
import com.example.demo.service.interfaces.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/com.solponge/admin")
public class AdminController {
    private final MemberService memberService;
    private final CartService cartService;

    @GetMapping("/member")
    public String member(Model model, HttpServletRequest request){
        getLoginMember(request);
        List<Member> all = memberService.findAll();
        model.addAttribute("members",all);
        return "admin/memberManageList";
    }

    @GetMapping("/member/{memberNum}/update")
    public String editMember(@PathVariable Long memberNum, Model model, HttpServletRequest request) {
        getLoginMember(request);
        Member member = memberService.findByNo(memberNum).get();
        log.info("adminMember={}",member);
        model.addAttribute("member", member);
        return "admin/memberManagePage";
    }

    @PostMapping("/member/{memberNum}/update")
    public String edit(HttpServletRequest request,
                       @ModelAttribute Member member,
                       @PathVariable Long memberNum) {
        log.info("member={}",member.getMemberPhone());
        log.info("memberNum={}",memberNum);
        getLoginMember(request);

        memberService.update(memberNum,member);


        return "redirect:/com.solponge/admin/member";
    }

    @GetMapping("/member/{memberNum}/delete")
    public String deleteMember(@PathVariable Long memberNum,HttpServletRequest request) {
        getLoginMember(request);
        cartService.deleteCartByMemberNum(memberNum);
        memberService.delete(memberService.findByNo(memberNum).get().getMemberNum());
        return "redirect:/com.solponge/admin/member";
    }

    private Member getLoginMember(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null ? (Member) session.getAttribute(SessionConst.LOGIN_MEMBER) : null;
    }
}
