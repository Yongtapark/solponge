package com.example.demo.service;

import com.example.demo.domain.payment.Payment;
import com.example.demo.domain.payment.PaymentGroup;
import com.example.demo.repository.payment.PaymentQueryRepository;
import com.example.demo.repository.payment.PaymentRepository;
import com.example.demo.repository.product.ProductQueryRepository;
import com.example.demo.service.interfaces.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final ProductQueryRepository productQueryRepository;
    private final PaymentQueryRepository paymentQueryRepository;

    @Override
    public Long save(Payment payment) {
        return paymentRepository.save(payment).getPaymentNum();
    }

    @Override
    public List<Payment> getPaymentList(Long memberNum) {
        return paymentRepository.findByMemberMemberNum(memberNum);
    }

    @Override
    public void addStock(Long paymentNum, Long productStock) {
        productQueryRepository.addStock(paymentNum,productStock);
    }

    @Override
    public void subtractStock(Long productNum, Long paymentStock) {
        productQueryRepository.subtractStock(productNum,paymentStock);
    }

    @Override
    public Map<Long, List<Payment>> showPaymentList(Long memberNum) {
        return paymentQueryRepository.showPaymentList(memberNum);
    }




}
