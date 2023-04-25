package com.example.demo.service;

import com.example.demo.domain.companyScrap.CompanyScrap;
import com.example.demo.domain.infoScrap.InfoScrap;
import com.example.demo.repository.companyScrap.CompanyScrapRepository;
import com.example.demo.repository.infoScrap.InfoScrapRepository;
import com.example.demo.service.interfaces.JobScrapService;
import com.example.demo.utils.SearchCond;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public List<InfoScrap> infoScrapList(Long memberNum) {
        return infoScrapRepository.findAllByMemberNum(memberNum);
    }

    @Override
    public List<CompanyScrap> companyScrapList(Long memberNum) {
        return companyScrapRepository.findAllByMemberNum(memberNum);
    }

    @Override
    public Page<CompanyScrap> getCompanyScrapList(Long memberNum, Pageable pageable) {
        return companyScrapRepository.findAllByMemberNum(memberNum,pageable);
    }

    @Override
    public Page<InfoScrap> getInfoScrapList(Long memberNum, Pageable pageable) {
        return infoScrapRepository.findAllByMemberNum(memberNum,pageable);
    }

    @Override
    public Page<InfoScrap> search(SearchCond cond, Pageable pageable) {
        return null;
    }
}
