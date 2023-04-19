package com.example.demo.domain.payment;

import lombok.Data;

@Data
public class OrderList {
    Long cartItemNum;
    Long productNum;
    Long productPrice;
    Long paymentStock;
}
