package com.example.demo.domain.member;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
@Data
@Entity
public class MemberJoinForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long memberNo;
    @Enumerated(EnumType.STRING)
    private Grade memberGrade = Grade.BASIC;
    @NotEmpty
    private String memberId;
    @NotEmpty
    private String memberPwd;
    @NotEmpty
    @Transient
    private String memberPwdCheck;
    @NotEmpty
    private String memberName;
    /**
     * 주소
     */

    private String memberAddress;
    @Transient
    @NotEmpty
    private String memberAddress1;
    @Transient
    @NotEmpty
    private String memberAddress2;
    @Transient
    @NotEmpty
    private String memberAddress3;
    /**
     * 이메일
     */

    private String memberEmail;
    @Transient
    @NotEmpty
    private String memberEmail1;
    @Transient
    @NotEmpty
    private String memberEmail2;
    /**
     * 휴대폰
     */
    private String memberPhone;
    @Transient
    @NotEmpty
    private String memberPhone1;
    @Transient
    @NotEmpty
    private String memberPhone2;
    @Transient
    @NotEmpty
    private String memberPhone3;

    public MemberJoinForm() {
    }

    public MemberJoinForm(String memberId, String memberPwd, String memberName, String memberAddress, String memberEmail, String memberPhone) {
        this.memberId = memberId;
        this.memberPwd = memberPwd;
        this.memberName = memberName;
        this.memberAddress = memberAddress;
        this.memberEmail = memberEmail;
        this.memberPhone = memberPhone;
    }
}
