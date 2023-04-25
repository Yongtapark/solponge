package com.example.demo.repository.companyScrap;

import com.example.demo.domain.companyScrap.CompanyScrap;
import com.example.demo.domain.infoScrap.InfoScrap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyScrapRepository extends JpaRepository<CompanyScrap,Long> {
    List<CompanyScrap> findAllByMemberNum(Long memberNum);
    void deleteByMemberNumAndAndCompanyName(Long memberNum, String companyName);

    Page<CompanyScrap> findAllByMemberNum(Long memberNum, Pageable pageable);
}
