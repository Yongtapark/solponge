package com.example.demo.service.interfaces;

import com.example.demo.domain.payment.Payment;
import com.example.demo.domain.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PaymentService {
    Long save(Payment payment);
    List<Payment> getPaymentList(Long memberNum);

    void addStock(Long paymentNum, Long productNum);
    void subtractStock(Long productNum, Long paymentStock);
}
