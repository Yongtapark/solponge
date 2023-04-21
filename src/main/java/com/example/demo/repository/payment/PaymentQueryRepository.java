package com.example.demo.repository.payment;

import com.example.demo.domain.payment.Payment;

import java.util.LinkedHashMap;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;


import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.demo.domain.payment.QPayment.*;

@Slf4j
public class PaymentQueryRepository {
    private final JPAQueryFactory queryFactory;
    private final PaymentRepository paymentRepository;

    public PaymentQueryRepository(EntityManager em, PaymentRepository paymentRepository) {
        this.queryFactory = new JPAQueryFactory(em);
        this.paymentRepository = paymentRepository;
    }

    public Map<Long, List<Payment>> showPaymentList(Long memberNum) {
        List<Payment> payments = queryFactory.selectFrom(payment)
                .where(payment.member.memberNum.eq(memberNum))
                .orderBy(payment.paymentGroup.desc())
                .fetch();

        return payments.stream()
                .collect(Collectors.groupingBy(Payment::getPaymentGroup, LinkedHashMap::new, Collectors.toList()));
    }


    public void cancelPayment(Long paymentNum, Long memberNum) {
        queryFactory.update(payment)
                .set(payment.visible, 0)
                .where(payment.paymentNum.eq(paymentNum)
                        .and(payment.member.memberNum.eq(memberNum)))
                .execute();

        // 해당 Payment에 대응하는 Product의 재고(stock)를 증가시키는 로직 추가
    }

    public void updateProductStock(Long productNum, Integer newStock) {
        queryFactory.update(payment.product)
                .set(payment.product.productStock, newStock)
                .where(payment.product.productNum.eq(productNum))
                .execute();
    }

}
