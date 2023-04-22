package com.example.demo.domain.product;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long productNum;
    private String productTitle;
    private String productSubtitle;
    private String productWriter;
    private int productPrice;
    private String productImg;
    private Date productDate;
    private String productPage;
    private String productCode;
    private int productStock;
    private int productSale;
    private int productVisit;
    private Boolean isDeleted = true;


    //생성자 오버로딩

    public Product(Long productNum, String productTitle, int productPrice, int productStock) {
        this.productNum = productNum;
        this.productTitle = productTitle;
        this.productPrice = productPrice;
        this.productStock = productStock;
    }



}
