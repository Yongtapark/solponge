package com.example.demo.repository.infoScrapRepository;

import com.example.demo.domain.infoScrap.InfoScrap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfoScrapRepository extends JpaRepository<InfoScrap,Long> {
    void deleteByMemberNumAndInfoName(Long memberNum,String infoName);
}
