package com.example.demo.repository.product;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductSearchCond {

    private String searchSelect;
    private String searchValue;

    public ProductSearchCond(String searchSelect, String searchValue) {
        this.searchSelect = searchSelect;
        this.searchValue = searchValue;
    }
}
