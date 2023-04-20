package com.example.demo.domain.payment;

import lombok.Data;

import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class PayForm {
    private Long paymentNum;
    private Long memberNum;
    private String memberName;
    private List<OrderList> orderList;
    private Long totalPrice;
    private String deliveryInfo;

    /**
     * 주소
     */
    private String memberAddress;
    private String memberAddress1;
    private String memberAddress2;
    private String memberAddress3;

    /**
     * 휴대폰
     */
    private String memberPhone;
    private String memberPhone1;
    private String memberPhone2;
    private String memberPhone3;
    /**
     * 이메일
     */
    private String memberEmail;
    private String memberEmail1;
    private String memberEmail2;


}
