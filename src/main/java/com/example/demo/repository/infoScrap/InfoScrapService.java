package com.example.demo.repository.infoScrap;

import com.example.demo.domain.infoScrap.InfoScrap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfoScrapService extends JpaRepository<InfoScrap,Long> {
    void deleteByMemberNumAndAndInfoName(Long memberNum, String infoName);

}
