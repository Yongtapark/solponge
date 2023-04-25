package com.example.demo.service.interfaces;

import com.example.demo.domain.companyScrap.CompanyScrap;
import com.example.demo.domain.infoScrap.InfoScrap;
import com.example.demo.domain.member.Member;
import com.example.demo.utils.SearchCond;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JobScrapService {
    CompanyScrap insertJobScrapCompany(CompanyScrap companyScrap);
    InfoScrap insertJobScrapInfoScrap(InfoScrap infoScrap);
    void deleteJobScrapCompany(Long memberNum,String companyName);
    void deleteJobScrapInfoScrap(Long memberNum,String infoSName);

    List<InfoScrap> infoScrapList(Long memberNum);
    List<CompanyScrap> companyScrapList(Long memberNum);


    Page<CompanyScrap> getCompanyScrapList(Long memberNum, Pageable pageable);
    Page<InfoScrap> getInfoScrapList(Long memberNum, Pageable pageable);

    Page<InfoScrap> search(SearchCond cond, Pageable pageable);
}
