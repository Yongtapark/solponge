package com.example.demo.repository.payment;

import com.example.demo.domain.payment.Payment;

import java.util.LinkedHashMap;


import com.example.demo.utils.SearchCond;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;


import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.demo.domain.payment.QPayment.*;

@Slf4j
public class PaymentQueryRepository {
    private final JPAQueryFactory query;
    private final PaymentRepository paymentRepository;

    public PaymentQueryRepository(EntityManager em, PaymentRepository paymentRepository) {
        this.query = new JPAQueryFactory(em);
        this.paymentRepository = paymentRepository;
    }

    /**
     * 주문정보 페이지
     */
    public Map<Long, List<Payment>> showPaymentList(Long memberNum) {
        List<Payment> payments = query.selectFrom(payment)
                .where(payment.isDeleted.eq(Boolean.FALSE).and(payment.member.memberNum.eq(memberNum)))
                .orderBy(payment.paymentOrderNum.desc())
                .fetch();

        return payments.stream()
                .collect(Collectors.groupingBy(Payment::getPaymentOrderNum, LinkedHashMap::new, Collectors.toList()));
    }


    /**
     * 페이징, 검색
     */
    /*검색조건 결과*/
    private List<Payment> searchPayments(SearchCond cond){
        return query.selectFrom(payment)
                .where(payment.isDeleted.eq(Boolean.FALSE)
                        .and(searchBySelect((cond.getSearchSelect()), cond.getSearchValue())))
                .orderBy(payment.paymentDate.desc())
                .fetch();
    }
    /*페이징 값*/
    private List<Payment> paginatePayment(List<Payment> members, Pageable pageable){
        int start = (int)pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), members.size());
        return members.subList(start,end);
    }
    /*페이징 및 검색*/
    public Page<Payment> search(SearchCond cond, Pageable pageable) {
        List<Payment> searchPayments = searchPayments(cond);
        List<Payment> paginatePayment = paginatePayment(searchPayments, pageable);
        return new PageImpl<>(paginatePayment, pageable, searchPayments.size());
    }

    /*검색조건*/
    private BooleanExpression searchBySelect(String searchSelect, String searchValue){
        if(StringUtils.hasText(searchSelect)&& StringUtils.hasText(searchValue)){
            switch (searchSelect){
                case "all":
                    return payment.paymentOrderNum.stringValue().contains(searchValue)
                            .or(payment.member.memberId.contains(searchValue));
                case "paymentGroup":
                    return payment.paymentOrderNum.stringValue().contains(searchValue);
                case"memberName":
                    return payment.member.memberId.contains(searchValue);
            }
        }
        return null;
    }



    public void deleteLogical(Long paymentNum, Long memberNum) {
        query.update(payment)
                .set(payment.isDeleted, Boolean.TRUE)
                .where(payment.paymentNum.eq(paymentNum))
                .execute();

        // 해당 Payment에 대응하는 Product의 재고(stock)를 증가시키는 로직 추가
    }

    public void updateProductStock(Long productNum, Integer newStock) {
        query.update(payment.product)
                .set(payment.product.productStock, newStock)
                .where(payment.product.productNum.eq(productNum))
                .execute();
    }

}
