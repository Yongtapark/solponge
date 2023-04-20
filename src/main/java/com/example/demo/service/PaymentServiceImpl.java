package com.example.demo.service;

import com.example.demo.domain.payment.Payment;
import com.example.demo.repository.payment.PaymentRepository;
import com.example.demo.repository.product.ProductQueryRepository;
import com.example.demo.service.interfaces.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final ProductQueryRepository productQueryRepository;

    @Override
    public Long save(Payment payment) {
        return paymentRepository.save(payment).getPaymentNum();
    }

    @Override
    public List<Payment> getPaymentList(Long memberNum) {
        return paymentRepository.findByMember(memberNum);
    }

    @Override
    public void addStock(Long paymentNum, Long productStock) {
        productQueryRepository.addStock(paymentNum,productStock);
    }

    @Override
    public void subtractStock(Long productNum, Long paymentStock) {
        productQueryRepository.subtractStock(productNum,paymentStock);
    }
}
