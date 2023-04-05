package com.example.demo.repository.member;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberSearchCond {

    private String memberName;
    private String memberId;


    public MemberSearchCond(String memberName, String memberId) {
        this.memberName = memberName;
        this.memberId = memberId;
    }




}
