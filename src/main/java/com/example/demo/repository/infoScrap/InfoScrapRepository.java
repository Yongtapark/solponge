package com.example.demo.repository.infoScrap;

import com.example.demo.domain.companyScrap.CompanyScrap;
import com.example.demo.domain.infoScrap.InfoScrap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InfoScrapRepository extends JpaRepository<InfoScrap,Long> {
    List<InfoScrap> findAllByMemberNum(Long memberNum);
    void deleteByMemberNumAndInfoName(Long memberNum,String infoName);
    Page<InfoScrap> findAllByMemberNum(Long memberNum, Pageable pageable);

}
