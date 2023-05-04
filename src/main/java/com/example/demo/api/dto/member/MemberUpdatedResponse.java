package com.example.demo.api.dto.member;

import lombok.Data;

@Data
public class MemberUpdatedResponse {
    private Long id;

    public MemberUpdatedResponse(Long id) {
        this.id = id;
    }
}
