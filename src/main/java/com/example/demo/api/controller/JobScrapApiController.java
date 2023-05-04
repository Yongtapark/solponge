package com.example.demo.api.controller;

import com.example.demo.domain.companyScrap.CompanyScrap;
import com.example.demo.domain.infoScrap.InfoScrap;
import com.example.demo.service.interfaces.JobScrapService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@Slf4j
@RequiredArgsConstructor
@RestController
public class JobScrapApiController {
    private final JobScrapService jobScrapService;

    /**
     * 회사 스크랩
     */

    /*회사 스크랩*/
    @PostMapping("/scrap/company")
    public ResponseEntity<CompanyScrap> scrapCompanyInsert(@RequestBody CompanyScrap companyScrap) {
        ResponseEntity<CompanyScrap> body = ResponseEntity.status(HttpStatus.CREATED).body(jobScrapService.insertJobScrapCompany(companyScrap));
        return body;
    }
    /*회사 스크랩 제거*/
    @DeleteMapping("/scrap/company/delete")
    public ResponseEntity<Void> scrapCompanyDelete(@RequestBody CompanyScrap companyScrap) {
        jobScrapService.deleteJobScrapCompany(companyScrap.getMemberNum(), companyScrap.getCompanyName());
        return ResponseEntity.noContent().build();
    }

    /*마이페이지 내 회사 스크랩 제거*/
    @PostMapping("/scrap/company/delete/mypage")
    public String scrapCompanyDeleteMypage(@RequestBody CompanyScrap companyScrap) {
        jobScrapService.deleteJobScrapCompany(companyScrap.getMemberNum(),companyScrap.getCompanyName());
        return "redirect:./";
    }

    /*공고 찜*/
    @PostMapping("/scrap/job")
    public ResponseEntity<InfoScrap> scrapJobInsert(@RequestBody InfoScrap infoScrap) {
        return ResponseEntity.status(HttpStatus.CREATED).body(jobScrapService.insertJobScrapInfoScrap(infoScrap));
    }

    /*공고 삭제*/
    @DeleteMapping("/scrap/job/delete")
    public ResponseEntity<Void> scrapJobDelete(@RequestBody InfoScrap infoScrap) {
        jobScrapService.deleteJobScrapInfoScrap(infoScrap.getMemberNum(), infoScrap.getJobInfoNum());
        return ResponseEntity.noContent().build();
    }



}
