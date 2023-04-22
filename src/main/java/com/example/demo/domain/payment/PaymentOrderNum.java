package com.example.demo.domain.payment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class PaymentOrderNum {

    private Map<Long,List<Payment>> payments;


    public PaymentOrderNum(Map<Long, List<Payment>> payments) {
        this.payments = payments;
    }
}
