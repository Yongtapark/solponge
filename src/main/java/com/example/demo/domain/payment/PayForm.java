package com.example.demo.domain.payment;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class PayForm {
    private Long paymentNum;
    private String memberName;
    private List<OrderList> orderList;
    private Long totalPrice;
    private String deliveryInfo;

    /**
     * 주소
     */
    private String memberAddress;
    @NotEmpty
    private String memberAddress1;
    @NotEmpty
    private String memberAddress2;
    @NotEmpty
    private String memberAddress3;

    /**
     * 휴대폰
     */
    private String memberPhone;

    @NotEmpty
    private String memberPhone1;

    @NotEmpty
    private String memberPhone2;

    @NotEmpty
    private String memberPhone3;


}
