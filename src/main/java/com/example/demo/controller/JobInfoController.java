package com.example.demo.controller;

import com.example.demo.domain.jobInfo.JobInfo;
import com.example.demo.domain.member.Member;
import com.example.demo.domain.member.login.session.SessionConst;
import com.example.demo.domain.product.Product;
import com.example.demo.service.interfaces.JobInfoService;
import com.example.demo.utils.SearchCond;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/com.solponge")
@RequiredArgsConstructor
@Slf4j
public class JobInfoController {
    private final JobInfoService jobInfoService;

    @GetMapping("/jobInfo")
    public String jobinfolist(Model model,
                              HttpServletRequest request,
                              @PageableDefault(page = 0, size = 10, sort ="jobInfoNum",direction = Sort.Direction.DESC) Pageable pageable,
                              String searchSelect, String searchValue){
        Member loginMember = getLoginMember(request);
        model.addAttribute("member",loginMember);

        Page<JobInfo> paginatedJobInfoList = null;
        if (searchSelect==null && searchValue==null){
            log.info("paginatedJobInfoList진입==================");
            paginatedJobInfoList = jobInfoService.findAll(pageable);
            log.info("paginatedJobInfoList={}",paginatedJobInfoList);
        }else {
            log.info("===검색어 있음===");
            SearchCond cond = new SearchCond(searchSelect, searchValue);
            log.info("cond={}",cond);
            paginatedJobInfoList = jobInfoService.search(cond, pageable);
        }


        int nowPage= paginatedJobInfoList.getPageable().getPageNumber()+1 ;
        int totalPages = paginatedJobInfoList.getTotalPages();
        int startPage = Math.max(nowPage - 5, 1);
        int endPage = Math.min(startPage + 9, totalPages);

        if (endPage - startPage < 9 && totalPages > 9) {
            startPage = Math.max(endPage - 9, 1);
        }

        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("paginatedjobinfo",paginatedJobInfoList);


        return "jobInfo/jobInfoList";
    }

    private Member getLoginMember(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null ? (Member) session.getAttribute(SessionConst.LOGIN_MEMBER) : null;
    }


}
