package com.example.demo.domain.payment;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Data
public class PaymentOrderNum {

    private Map<Long,List<Payment>> payments;


    public PaymentOrderNum(Map<Long, List<Payment>> payments) {
        this.payments = payments;
    }
}
