package com.example.demo.domain.jobInfo;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class JobInfo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobInfoNum;
    private String jobInfoField;
    private String jobInfoCompanyName;
    private String jobInfoPostingName;
    private String jobInfoFieldTagList;
    private String jobInfoQualification;
    private String jobInfoWorkType;
    private String jobInfoWorkArea;
    private String jobInfoDeadline;
    private String jobInfoPageCode;
    private String jobInfoLink;
}
