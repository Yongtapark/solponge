package com.example.demo.repository;

import lombok.Data;

@Data
public class MemberSearchCond {

    private String memberName;
    private String memberId;

    public MemberSearchCond() {
    }

    public MemberSearchCond(String memberName, String memberId) {
        this.memberName = memberName;
        this.memberId = memberId;
    }
}
