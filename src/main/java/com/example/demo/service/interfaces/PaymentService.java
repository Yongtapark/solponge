package com.example.demo.service.interfaces;

import com.example.demo.domain.payment.Payment;
import com.example.demo.domain.payment.PaymentGroup;
import com.example.demo.domain.product.Product;
import com.example.demo.domain.utils.SearchCond;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface PaymentService {
    Long save(Payment payment);
    Payment findByPaymentNum(Long paymentNum);
    Payment updateDeliveryNum(Long paymentNum,Long deliverNum);
    Page<Payment> findAll(Pageable pageable);
    Page<Payment> search(SearchCond cond, Pageable pageable);

    void addStock(Long paymentNum, Long productNum);
    void subtractStock(Long productNum, Long paymentStock);

    Map<Long, List<Payment>> showPaymentList(Long memberNum);

}
