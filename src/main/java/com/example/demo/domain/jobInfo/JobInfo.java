package com.example.demo.domain.jobInfo;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    public JobInfo(Long jobInfoNum, String jobInfoCompanyName, String jobInfoPostingName, String jobInfoQualification, String jobInfoWorkType, String jobInfoDeadline) {
        this.jobInfoNum = jobInfoNum;
        this.jobInfoCompanyName = jobInfoCompanyName;
        this.jobInfoPostingName = jobInfoPostingName;
        this.jobInfoQualification = jobInfoQualification;
        this.jobInfoWorkType = jobInfoWorkType;
        this.jobInfoDeadline = jobInfoDeadline;
    }
}
