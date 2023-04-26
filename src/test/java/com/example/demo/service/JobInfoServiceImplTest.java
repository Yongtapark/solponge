package com.example.demo.service;

import com.example.demo.domain.jobInfo.JobInfo;
import com.example.demo.repository.jobInfo.JobInfoRepository;
import com.example.demo.service.interfaces.JobInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@SpringBootTest
@Slf4j
@Transactional
class JobInfoServiceImplTest {
    @Autowired
    JobInfoService jobInfoService;

    @Autowired
    JobInfoRepository jobInfoRepository;

    @Test
    void findAll() {
        Pageable pageable = PageRequest.of(0, 10);
        jobInfoService.findAll(pageable);
    }

    @Test
    void findMyJobInfo(){
        PageRequest pageable = PageRequest.of(0, 10);
        Page<JobInfo> jobInfos = jobInfoService.myPageScrapJobInfo(3L, pageable);
        log.info("jobInfos={}",jobInfos.getContent().size());

        Map<String, Long> announcement = jobInfoService.myScrapCompanyAnnouncements(3L);
        log.info("announcement={}",announcement);
    }
}