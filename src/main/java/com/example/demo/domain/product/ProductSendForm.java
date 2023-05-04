package com.example.demo.domain.product;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ProductSendForm {
    private Long productNum;
    private Long quantity;
    private Boolean isCart;
}
