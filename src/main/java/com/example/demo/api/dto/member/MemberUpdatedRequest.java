package com.example.demo.api.dto.member;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class MemberUpdatedRequest {
    @NotEmpty
    private String memberPwd;
    @NotEmpty
    private String memberPwdCheck;
    @NotEmpty
    private String memberName;
    /**
     * 주소
     */

    private String memberAddress;
    @NotEmpty
    private String memberAddress1;
    @NotEmpty
    private String memberAddress2;
    @NotEmpty
    private String memberAddress3;
    /**
     * 이메일
     */

    private String memberEmail;
    @NotEmpty
    private String memberEmail1;
    @NotEmpty
    private String memberEmail2;
    /**
     * 휴대폰
     */
    private String memberPhone;
    @NotEmpty
    private String memberPhone1;
    @NotEmpty
    private String memberPhone2;
    @NotEmpty
    private String memberPhone3;
}
