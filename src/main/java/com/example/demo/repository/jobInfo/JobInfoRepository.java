package com.example.demo.repository.jobInfo;

import com.example.demo.domain.jobInfo.JobInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobInfoRepository extends JpaRepository<JobInfo,Long> {
    Page<JobInfo> findAll(Pageable pageable);
}
