package com.example.demo.domain.infoScrap;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class InfoScrap {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long infoScrapNum;
    private Long memberNum;
    private String infoName;
    private LocalDateTime scrapTime=LocalDateTime.now();

}
