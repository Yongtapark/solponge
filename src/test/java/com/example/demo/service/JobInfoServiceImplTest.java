package com.example.demo.service;

import com.example.demo.domain.jobInfo.JobInfo;
import com.example.demo.repository.jobInfo.JobInfoRepository;
import com.example.demo.service.interfaces.JobInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
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
}