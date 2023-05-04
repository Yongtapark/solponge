package com.example.demo.api.dto.member;

import lombok.Getter;

@Getter
public class MemberCreatedResponse {
    private Long id;

    public MemberCreatedResponse(Long id) {
        this.id = id;
    }
}
