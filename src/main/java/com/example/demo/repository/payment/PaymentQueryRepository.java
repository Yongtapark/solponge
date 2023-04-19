/*
package com.example.demo.repository.payment;

import com.example.demo.domain.payment.Payment;

import com.querydsl.core.types.dsl.BooleanExpression;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;


import javax.persistence.EntityManager;
import java.util.List;

import static com.example.demo.domain.payment.QPayment.*;

@Slf4j
public class PaymentQueryRepository {
    private final JPAQueryFactory queryFactory;
    private final PaymentRepository paymentRepository;

    public PaymentQueryRepository(EntityManager em, PaymentRepository paymentRepository) {
        this.queryFactory = new JPAQueryFactory(em);
        this.paymentRepository = paymentRepository;
    }

    public List<Payment> findByMemberNo(Long memberNo) {

        BooleanExpression expression = payment.member.memberNum.eq(memberNo).and(payment.visible.eq(1));
        return queryFactory.selectFrom(payment)
                .where(expression)
                .orderBy(payment.paymentDate.desc())
                .fetch();
    }

    public void insertPayment(Payment payment) {
        paymentRepository.save(payment);
    }

    public void cancelPayment(Long paymentNum, Long productNum, Long memberNo) {
        queryFactory.update(payment)
                .set(payment.visible, 0)
                .where(payment.paymentNum.eq(paymentNum)
                        .and(payment.product.productNum.eq(productNum))
                        .and(payment.member.memberNum.eq(memberNo)))
                .execute();

        // 해당 Payment에 대응하는 Product의 재고(stock)를 증가시키는 로직 추가
    }

    public void updateProductStock(Long productNum, Integer newStock) {
        queryFactory.update(payment.product)
                .set(payment.product.productStock, newStock)
                .where(payment.product.productNum.eq(productNum))
                .execute();
    }

    public Integer selectProductStock(Long productNum) {
        return queryFactory.select(payment.product.productStock)
                .from(payment.product)
                .where(payment.product.productNum.eq(productNum))
                .fetchOne();
    }
}
*/
