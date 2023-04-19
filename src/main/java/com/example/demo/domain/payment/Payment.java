package com.example.demo.domain.payment;

import com.example.demo.domain.member.Member;
import com.example.demo.domain.product.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Payment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentNum; //내부용 주문번호
    private String paymentOrderNum = ""; //외부용 주문번호
    @ManyToOne
    @JoinColumn(name = "member_num")
    private Member member;
    @ManyToOne
    @JoinColumn(name = "product_num")
    private Product product;
    private Long paymentStock;
    private String paymentDate;
    private String paymentPhone;
    private  String paymentEmail;
    private String paymentAddress;
    private  String deliveryInfo;
    private long deliveryNum;
    private int visible=1;
    private int success=0;

    public Payment(Member member, Product product, Long paymentStock, String paymentDate, String paymentPhone, String paymentEmail, String paymentAddress, String deliveryInfo) {
        this.member = member;
        this.product = product;
        this.paymentStock = paymentStock;
        this.paymentDate = paymentDate;
        this.paymentPhone = paymentPhone;
        this.paymentEmail = paymentEmail;
        this.paymentAddress = paymentAddress;
        this.deliveryInfo = deliveryInfo;
    }
}
