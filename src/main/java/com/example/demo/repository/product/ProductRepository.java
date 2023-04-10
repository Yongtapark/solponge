package com.example.demo.repository.product;

import com.example.demo.domain.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends JpaRepository<Product,Long>, PagingAndSortingRepository<Product,Long> {
    Page<Product> findByProductTitleContaining(String searchKeyword, Pageable pageable);
}
