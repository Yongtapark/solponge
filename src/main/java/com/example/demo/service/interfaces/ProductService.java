package com.example.demo.service.interfaces;

import com.example.demo.domain.member.Member;
import com.example.demo.domain.product.Product;
import com.example.demo.repository.product.ProductSearchCond;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Long save(Product product);
    void update(Long productNo, Product product);
    void delete(Long productNo);
    Optional<Product> findByNo(Long productNo);
    List<Product> findAll();
    List<Product> findProducts(ProductSearchCond cond);
    List<Product> newTop8List();
    List<Product> popTop8List();



}
