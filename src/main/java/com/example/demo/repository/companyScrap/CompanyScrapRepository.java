package com.example.demo.repository.companyScrap;

import com.example.demo.domain.companyScrap.CompanyScrap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyScrapRepository extends JpaRepository<CompanyScrap,Long> {
    void deleteByMemberNumAndAndCompanyName(Long memberNum, String companyName);
}
