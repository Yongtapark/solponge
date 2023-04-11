package com.example.demo.domain.cart;

import com.example.demo.domain.member.Member;
import com.example.demo.domain.product.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class CartItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemNum;
    @ManyToOne
    @JoinColumn(name = "member_num")
    private Member member;
    @ManyToOne
    @JoinColumn(name = "product_num")
    private Product product;
    private int cartItemStock;


    public CartItem(Member member, Product product, int cartItemStock) {
        this.member = member;
        this.product = product;
        this.cartItemStock = cartItemStock;
    }

    public CartItem(Long cartItemNum, Member member, Product product, int cartItemStock) {
        this.cartItemNum = cartItemNum;
        this.member = member;
        this.product = product;
        this.cartItemStock = cartItemStock;
    }


    public int getTotalPrice(){
        int totalPrice = 0;
        if (this.cartItemStock > 0) {
            totalPrice += this.cartItemStock * this.product.getProductPrice();
        }
        return totalPrice;
    }


}
