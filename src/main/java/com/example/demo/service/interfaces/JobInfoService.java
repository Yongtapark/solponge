package com.example.demo.service.interfaces;

import com.example.demo.domain.jobInfo.JobInfo;
import com.example.demo.utils.SearchCond;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface JobInfoService {
    JobInfo getJobInfo(Long jobInfoNum);

    List<JobInfo> getJobInfoNewTop8List();
    /*전체 페이지*/
    Page<JobInfo> findAll(Pageable pageable);
    Page<JobInfo> search(SearchCond cond, Pageable pageable);
    /*마이페이지 스크랩*/
    Page<JobInfo> myPageScrapJobInfo(Long memberNum, Pageable pageable);
    Page<JobInfo> myPageScrapCompany(Long memberNum, Pageable pageable);
    /*스크랩한 회사의 공고 수*/
    Map<String,Long> MyScrapCompanyAnnouncement(Long memberNum);

    List<JobInfo> findAll();


}
