package com.example.demo.controller;

import com.example.demo.domain.jobInfo.JobInfo;
import com.example.demo.domain.member.Member;
import com.example.demo.domain.member.MemberJoinForm;
import com.example.demo.domain.member.login.session.SessionConst;
import com.example.demo.domain.payment.PaymentOrderNum;
import com.example.demo.service.interfaces.JobInfoService;
import com.example.demo.service.interfaces.JobScrapService;
import com.example.demo.service.interfaces.MemberService;
import com.example.demo.service.interfaces.PaymentService;
import com.example.demo.utils.SearchCond;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/com.solponge/member/{memberNum}")
public class MemberController {
    private final MemberService memberService;
    private final PaymentService paymentService;
    private final JobScrapService jobScrapService;
    private final JobInfoService jobInfoService;

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
    public String produtslist(Model model,
                              HttpServletRequest request,
                              @PageableDefault(page = 0,size = 5,sort = "paymentOrderNum",direction = Sort.Direction.ASC)Pageable pageable){
        Member loginMember = getLoginMember(request);
        PaymentOrderNum PaymentOrderNum = new PaymentOrderNum(paymentService.showPaymentList(loginMember.getMemberNum()));
        model.addAttribute("paymentListMap",PaymentOrderNum);

        return "member/paymentList";
    }

    @GetMapping("/jobScrap")
    public String getjobScrap(Model model,
                              HttpServletRequest request,
                              @PageableDefault(page = 0, size = 10, sort ="jobInfoNum",direction = Sort.Direction.DESC) Pageable pageable,
                              String searchSelect,
                              String searchValue) {
        Member loginMember = getLoginMember(request);
        model.addAttribute("member", loginMember);
        Page<JobInfo> paginatedJobInfo =null;
        if (searchSelect==null && searchValue==null){
            log.info("===검색어 없음===");
            paginatedJobInfo = jobInfoService.findAll(pageable);
        }else {
            log.info("===검색어 있음===");
            SearchCond cond = new SearchCond(searchSelect, searchValue);
            log.info("cond={}",cond);
            paginatedJobInfo = jobInfoService.search(cond, pageable);
        }

        Page<JobInfo> jobInScrap = jobInfoService.myPageScrapJobInfo(loginMember.getMemberNum(), pageable);
        Map<String, Long> announcement = jobInfoService.myScrapCompanyAnnouncements(loginMember.getMemberNum());
        Map<String, JobInfo> recentCompanyAnnouncement = jobInfoService.recentCompanyAnnouncement(loginMember.getMemberNum());

        model.addAttribute("jobInScrap",jobInScrap);
        model.addAttribute("announcement",announcement);
        model.addAttribute("recentCompanyAnnouncement",recentCompanyAnnouncement);

        int nowPage= jobInScrap.getPageable().getPageNumber()+1 ;
        int totalPages = jobInScrap.getTotalPages();
        int startPage = Math.max(nowPage - 5, 1);
        int endPage = Math.min(startPage + 9, totalPages);

        if (endPage - startPage < 9 && totalPages > 9) {
            startPage = Math.max(endPage - 9, 1);
        }

        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("totalPages",totalPages);


        return "jobInfo/memberScrap";
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

