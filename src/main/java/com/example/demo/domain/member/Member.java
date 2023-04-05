package com.example.demo.domain.member;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long memberNo;
    @Enumerated(EnumType.STRING)
    private Grade memberGrade = Grade.BASIC;
    private String memberId;
    private String memberPwd;
    private String memberName;
    private String memberAddress;
    private String memberEmail;
    private String memberPhone;
    private LocalDateTime memberDate = LocalDateTime.now();



    public Member(String memberId, String memberPwd, String memberName, String memberAddress, String memberEmail, String memberPhone) {
        this.memberId = memberId;
        this.memberPwd = memberPwd;
        this.memberName = memberName;
        this.memberAddress = memberAddress;
        this.memberEmail = memberEmail;
        this.memberPhone = memberPhone;
    }

    public Member(String memberPwd, String memberAddress, String memberEmail, String memberPhone) {
        this.memberPwd = memberPwd;
        this.memberAddress = memberAddress;
        this.memberEmail = memberEmail;
        this.memberPhone = memberPhone;
    }

    public Member(String memberPwd, String memberName, String memberAddress, String memberEmail, String memberPhone) {
        this.memberPwd = memberPwd;
        this.memberName = memberName;
        this.memberAddress = memberAddress;
        this.memberEmail = memberEmail;
        this.memberPhone = memberPhone;
    }

    public Member(Long memberNo, String memberId, String memberName) {
        this.memberNo = memberNo;
        this.memberId = memberId;
        this.memberName = memberName;
    }

}
