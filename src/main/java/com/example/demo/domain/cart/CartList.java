package com.example.demo.domain.cart;

import com.example.demo.domain.member.Member;
import com.example.demo.domain.product.Product;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)public class CartList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartNum;
    @ManyToOne
    private Member member;
    @ManyToOne
    private Product product;
    private String cartDate;
    private int cartItemStock;

    public CartList(Member member, Product product, String cartDate, int cartItemStock) {
        this.member = member;
        this.product = product;
        this.cartDate = cartDate;
        this.cartItemStock = cartItemStock;
    }

}
