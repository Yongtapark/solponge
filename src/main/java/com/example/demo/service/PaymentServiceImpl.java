package com.example.demo.service;

import com.example.demo.domain.payment.Payment;
import com.example.demo.domain.payment.PaymentGroup;
import com.example.demo.domain.utils.SearchCond;
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
    public Payment findByPaymentNum(Long paymentNum) {
        return paymentRepository.findById(paymentNum).get();
    }

    @Override
    public Payment updateDeliveryNum(Long paymentNum, Long deliverNum) {
        Payment payment = paymentRepository.findById(paymentNum).get();
        payment.setSuccess(1);

        payment.setDeliveryNum(deliverNum);
        return payment;
    }


    @Override
    public Page<Payment> findAll(Pageable pageable) {
        return paymentRepository.findAll(pageable);
    }

    @Override
    public Page<Payment> search(SearchCond cond, Pageable pageable) {
        return paymentQueryRepository.search(cond,pageable);
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
