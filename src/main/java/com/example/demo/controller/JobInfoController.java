package com.example.demo.controller;

import com.example.demo.domain.companyScrap.CompanyScrap;
import com.example.demo.domain.infoScrap.InfoScrap;
import com.example.demo.domain.jobInfo.JobInfo;
import com.example.demo.domain.member.Member;
import com.example.demo.domain.member.login.session.SessionConst;
import com.example.demo.service.interfaces.JobInfoService;
import com.example.demo.service.interfaces.JobScrapService;
import com.example.demo.utils.SearchCond;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/com.solponge")
@RequiredArgsConstructor
@Slf4j
public class JobInfoController {
    private final JobInfoService jobInfoService;
    private final JobScrapService jobScrapService;

    @GetMapping({"/jobInfo"})
    public String jobInfoList(Model model,
                              HttpServletRequest request,
                              @PageableDefault(page = 0, size = 10, sort ="jobInfoNum",direction = Sort.Direction.DESC) Pageable pageable,
                              String searchSelect, String searchValue){

        Member loginMember = getLoginMember(request);

        Page<JobInfo> paginatedJobInfoList = null;
        if ((searchSelect==null||searchSelect=="")&&(searchValue==null||searchValue=="")){
            paginatedJobInfoList = jobInfoService.findAllPage(pageable);
        }else {
            SearchCond cond = new SearchCond(searchSelect, searchValue);
            paginatedJobInfoList = jobInfoService.search(cond, pageable);
        }

        //회원의 스크랩된 정보
        if(loginMember!=null){
            List<InfoScrap> infoScraps = jobScrapService.infoScrapList(loginMember.getMemberNum());
            List<CompanyScrap> companyScraps = jobScrapService.companyScrapList(loginMember.getMemberNum());
            //이름들만 리스트로 저장
            List<String> infoNames = infoScraps.stream().map(InfoScrap::getInfoName).collect(Collectors.toList());
            List<String> companyNames = companyScraps.stream().map(CompanyScrap::getCompanyName).collect(Collectors.toList());

            model.addAttribute("infoNames", infoNames);
            model.addAttribute("companyNames", companyNames);
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
