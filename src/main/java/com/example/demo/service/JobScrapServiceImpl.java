package com.example.demo.service;

import com.example.demo.domain.companyScrap.CompanyScrap;
import com.example.demo.domain.infoScrap.InfoScrap;
import com.example.demo.repository.companyScrap.CompanyScrapRepository;
import com.example.demo.repository.infoScrapRepository.InfoScrapRepository;
import com.example.demo.service.interfaces.JobScrapService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class JobScrapServiceImpl implements JobScrapService {
    private final InfoScrapRepository infoScrapRepository;
    private final CompanyScrapRepository companyScrapRepository;


    @Override
    public CompanyScrap insertJobScrapCompany(CompanyScrap companyScrap) {
        return companyScrapRepository.save(companyScrap);
    }

    @Override
    public InfoScrap insertJobScrapInfoScrap(InfoScrap infoScrap) {
        return infoScrapRepository.save(infoScrap);
    }

    @Override
    public void deleteJobScrapCompany(Long memberNum, String companyName) {
        companyScrapRepository.deleteByMemberNumAndAndCompanyName(memberNum,companyName);
    }

    @Override
    public void deleteJobScrapInfoScrap(Long memberNum, String infoName) {
        infoScrapRepository.deleteByMemberNumAndInfoName(memberNum,infoName);
    }

    @Override
    public List<CompanyScrap> getCompanyScrapList(Long memberNum) {
        return null;
    }

    @Override
    public List<InfoScrap> getInfoScrapList(Long memberNum) {
        return null;
    }
}
