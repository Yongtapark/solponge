package com.example.demo.domain.infoScrap;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class InfoScrap {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long infoScrapNum;
    private Long jobInfoNum;
    private Long memberNum;
    private String infoName;
    private LocalDateTime scrapTime=LocalDateTime.now();

}
