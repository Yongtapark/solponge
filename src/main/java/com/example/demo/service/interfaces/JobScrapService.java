package com.example.demo.service.interfaces;

import com.example.demo.domain.companyScrap.CompanyScrap;
import com.example.demo.domain.infoScrap.InfoScrap;

import java.util.List;

public interface JobScrapService {
    CompanyScrap insertJobScrapCompany(CompanyScrap companyScrap);
    InfoScrap insertJobScrapInfoScrap(InfoScrap infoScrap);
    void deleteJobScrapCompany(Long memberNum,String companyName);
    void deleteJobScrapInfoScrap(Long memberNum,String infoSName);

    List<CompanyScrap> getCompanyScrapList(Long memberNum);
    List<InfoScrap> getInfoScrapList(Long memberNum);
}
