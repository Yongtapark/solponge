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
    private Long memberNo;
    @Enumerated(EnumType.STRING)
    private Grade memberGrade = Grade.BASIC;
   // @NotEmpty
    private String memberId;
  //  @NotEmpty
    private String memberPwd;
  //  @NotEmpty
  @Transient
    private String memberPwdCheck;
  //  @NotEmpty
    private String memberName;
    /**
     * 주소
     */

    private String memberAddress;
    @Transient
    private String memberAddress1;
    @Transient
    private String memberAddress2;
    @Transient
    private String memberAddress3;
    /**
     * 이메일
     */

    private String memberEmail;
    @Transient
  //  @NotEmpty
    private String memberEmail1;
    @Transient
   // @NotEmpty
    private String memberEmail2;
    /**
     * 휴대폰
     */
    private String memberPhone;
    @Transient
   // @NotEmpty
    private String memberPhone1;
    @Transient
  //  @NotEmpty
    private String memberPhone2;
    @Transient
   // @NotEmpty
    private String memberPhone3;

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
