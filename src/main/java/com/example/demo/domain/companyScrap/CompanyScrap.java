package com.example.demo.domain.companyScrap;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Data
@Entity
public class CompanyScrap {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyScrapNum;
    private Long memberNum;
    private String companyName;
    private LocalDateTime scrapTime = LocalDateTime.now();
}
