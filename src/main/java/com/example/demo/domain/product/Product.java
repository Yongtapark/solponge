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
    private int productGarbage;


    //생성자 오버로딩
    public Product(int productGarbage, Long productNum, String productTitle, String productSubtitle, String productWriter, int productPrice, String productImg, Date productDate, int productStock, int productSale, int productVisit, String productPage, String productCode) {
        this.productNum = productNum;
        this.productTitle = productTitle;
        this.productSubtitle = productSubtitle;
        this.productWriter = productWriter;
        this.productPrice = productPrice;
        this.productImg = productImg;
        this.productDate = productDate;
        this.productStock = productStock;
        this.productSale = productSale;
        this.productVisit = productVisit;
        this.productPage = productPage;
        this.productCode = productCode;
        this.productGarbage = productGarbage;
    }

    public Product(Long productNum, String productTitle, int productPrice, int productStock) {
        this.productNum = productNum;
        this.productTitle = productTitle;
        this.productPrice = productPrice;
        this.productStock = productStock;
    }

    /**
     * db에서 받아온 정보를 productVo 객체로 만드는 생성자
     * @param product_num
     * @param product_title
     * @param product_price
     */
    public Product(Long product_num, String product_title, int product_price) {
        this.productNum = product_num;
        this.productTitle = product_title;
        this.productPrice = product_price;
    }


}
