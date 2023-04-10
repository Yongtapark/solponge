package com.example.demo.service;

import com.example.demo.domain.product.Product;
import com.example.demo.repository.product.ProductQueryRepository;
import com.example.demo.repository.product.ProductRepository;
import com.example.demo.repository.product.ProductSearchCond;
import com.example.demo.service.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductQueryRepository productQueryRepository;


    @Override
    public Long save(Product product) {
        return productRepository.save(product).getProductNum();
    }

    @Override
    public void update(Long productNo, Product product) {
        Product findProduct = productRepository.findById(productNo).get();
        findProduct.setProductNum(product.getProductNum());
        findProduct.setProductTitle(product.getProductTitle());
        findProduct.setProductSubtitle(product.getProductSubtitle());
        findProduct.setProductWriter(product.getProductWriter());
        findProduct.setProductPrice(product.getProductPrice());
        findProduct.setProductImg(product.getProductImg());
        findProduct.setProductDate(product.getProductDate());
        findProduct.setProductPage(product.getProductPage());
        findProduct.setProductStock(product.getProductStock());
        findProduct.setProductCode(product.getProductCode());
        findProduct.setProductSale(product.getProductSale());
        findProduct.setProductVisit(product.getProductVisit());
    }

    @Override
    public void delete(Long productNo) {
        Product deleteProduct = productRepository.findById(productNo).get();
        productRepository.delete(deleteProduct);
    }

    @Override
    public Optional<Product> findByNo(Long productNo) {
        return productRepository.findById(productNo);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getproductList() {
        return productQueryRepository.getProductList();
    }

    @Override
    public List<Product> newTop8List() {
        return productQueryRepository.findNewTop8Products();
    }

    @Override
    public List<Product> popTop8List() {
        return productQueryRepository.findPopularTop8Products();
    }

    @Override
    public Page<Product> search(ProductSearchCond cond, Pageable pageable) {
        return productQueryRepository.search(cond,pageable);
    }

    @Override
    public Product getProduct(int productNum) {
        return productQueryRepository.getProduct(productNum);
    }

    @Override
    public void deleteProduct(int productNum) {
        productQueryRepository.deleteProduct(productNum);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> productSearchList(String searchKeyword, Pageable pageable) {
        return productRepository.findByProductTitleContaining(searchKeyword,pageable);
    }
}
