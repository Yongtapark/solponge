package com.example.demo.domain.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SearchCond {

    private String searchSelect;
    private String searchValue;

    public SearchCond(String searchSelect, String searchValue) {
        this.searchSelect = searchSelect;
        this.searchValue = searchValue;
    }
}
