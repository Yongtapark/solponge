package com.example.demo.domain.companyScrap;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CompanyScrap {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyScrapNum;
    private Long memberNum;
    private String companyName;
    private LocalDateTime scrapTime = LocalDateTime.now();
}
