package com.example.demo.service.interfaces;

import com.example.demo.domain.jobInfo.JobInfo;
import com.example.demo.utils.SearchCond;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JobInfoService {
    JobInfo getJobInfo(Long jobInfoNum);
    Long getJobInfoCount();
    List<JobInfo> getJobInfoNewTop8List();
    List<JobInfo> getCompanyTojobinfoList(String companyName);
    List<JobInfo> getCompanyInScrapList(String[] companyNames);
    List<JobInfo> getInfoInScrapList(String[] infoNames);

    Page<JobInfo> findAll(Pageable pageable);
    Page<JobInfo> search(SearchCond cond, Pageable pageable);

    Page<JobInfo> myPageScrapJobInfo(Long memberNum, Pageable pageable);
    Page<JobInfo> myPageScrapCompany(Long memberNum, Pageable pageable);

    List<JobInfo> findAll();


}
