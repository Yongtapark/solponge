package com.example.demo.service;

import com.example.demo.domain.jobInfo.JobInfo;
import com.example.demo.repository.jobInfo.JobInfoQueryRepository;
import com.example.demo.repository.jobInfo.JobInfoRepository;
import com.example.demo.service.interfaces.JobInfoService;
import com.example.demo.utils.SearchCond;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
@Slf4j
public class JobInfoServiceImpl implements JobInfoService {
    private final JobInfoRepository jobInfoRepository;
    private final JobInfoQueryRepository jobInfoQueryRepository;


    public JobInfoServiceImpl(JobInfoRepository jobInfoRepository, JobInfoQueryRepository jobInfoQueryRepository) {
        this.jobInfoRepository = jobInfoRepository;
        this.jobInfoQueryRepository = jobInfoQueryRepository;
    }

    @Override
    public JobInfo getJobInfo(Long jobInfoNum) {
        return null;
    }

    @Override
    public Long getJobInfoCount() {
        return null;
    }

    @Override
    public List<JobInfo> getJobInfoNewTop8List() {
        return jobInfoQueryRepository.getJopInfoNewTop8List();
    }

    @Override
    public List<JobInfo> getCompanyTojobinfoList(String companyName) {
        return null;
    }

    @Override
    public List<JobInfo> getCompanyInScrapList(String[] companyNames) {
        return null;
    }

    @Override
    public List<JobInfo> getInfoInScrapList(String[] infoNames) {
        return null;
    }

    @Override
    public Page<JobInfo> findAll(Pageable pageable) {
        return jobInfoRepository.findAll(pageable);
    }

    @Override
    public Page<JobInfo> search(SearchCond cond, Pageable pageable) {
        return jobInfoQueryRepository.search(cond,pageable);
    }

    @Override
    public Page<JobInfo> myPageScrapJobInfo(Long memberNum, Pageable pageable) {
        return jobInfoQueryRepository.myScrapjobInfoPage(memberNum,pageable);
    }

    @Override
    public Page<JobInfo> myPageScrapCompany(Long memberNum, Pageable pageable) {
        return jobInfoQueryRepository.myScrapCompanyPage(memberNum,pageable);
    }

    @Override
    public Map<String, Long> MyScrapCompanyAnnouncement(Long memberNum) {
        return jobInfoQueryRepository.MyScrapCompanyAnnouncement(memberNum);
    }

    @Override
    public List<JobInfo> findAll() {
        return jobInfoRepository.findAll();
    }
}
