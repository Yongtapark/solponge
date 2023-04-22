package com.example.demo.service.interfaces;

import com.example.demo.domain.product.Product;
import com.example.demo.domain.utils.SearchCond;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Long save(Product product);
    void update(Long productNo, Product product);
    void delete(Long productNo);
    Optional<Product> findByNo(Long productNo);
    List<Product> findAll();
    List<Product> getproductList();
    List<Product> newTop8List();
    List<Product> popTop8List();

    public Page<Product> search(SearchCond cond, Pageable pageable);
    public Product getProduct(Long productNum);
    public void deleteProduct(Long productNum);
    Page<Product> findAll(Pageable pageable);



}
