package com.example.demo.repository.product;

import com.example.demo.domain.product.Product;
import com.example.demo.domain.utils.SearchCond;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.example.demo.domain.product.QProduct.*;
@Slf4j
public class ProductQueryRepository {

    private final JPAQueryFactory query;
    private final ProductRepository productRepository;

    public ProductQueryRepository(EntityManager em, ProductRepository productRepository) {
        this.query = new JPAQueryFactory(em);
        this.productRepository = productRepository;
    }

    private List<Product> searchProducts(SearchCond cond){
        return query.selectFrom(product)
                .where(product.isDeleted
                        .eq(Boolean.FALSE)
                        .and(searchBySelect((cond.getSearchSelect()), cond.getSearchValue())))
                .orderBy(product.productDate.desc())
                .fetch();
    }

    private List<Product> paginateProducts(List<Product> products, Pageable pageable){
        int start = (int)pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), products.size());
        return products.subList(start,end);
    }

    public Page<Product> search(SearchCond cond, Pageable pageable) {
        List<Product> searchedMembers = searchProducts(cond);
        List<Product> paginatedMembers = paginateProducts(searchedMembers, pageable);
        return new PageImpl<>(paginatedMembers, pageable, searchedMembers.size());
    }

    private BooleanExpression searchBySelect(String searchSelect, String searchValue) {
        log.info("===Enter search===");
        if (StringUtils.hasText(searchSelect) && StringUtils.hasText(searchValue)) {
            switch (searchSelect) {
                case "all":
                    return product.productTitle.contains(searchValue)
                            .or(product.productWriter.contains(searchValue));
                case "productTitle":
                    return product.productTitle.contains(searchValue);
                case "productWriter":
                    return product.productWriter.contains(searchValue);
            }
        }
        return null;
    }

    public List<Product> findAll(Pageable pageable){
        return query.selectFrom(product)
                .where(product.isDeleted.eq(Boolean.FALSE))
                .orderBy(product.productDate.desc())
                .fetch();
    }

    public List<Product> findNewTop8Products() {
        return query.selectFrom(product)
                .where(product.isDeleted.eq(Boolean.FALSE))
                .orderBy(product.productDate.desc())
                .limit(8)
                .fetch();
    }

    public List<Product> findPopularTop8Products() {
        return query.selectFrom(product)
                .where(product.isDeleted.eq(Boolean.FALSE))
                .orderBy(product.productVisit.desc())
                .limit(8)
                .fetch();
    }

    public List<Product> getProductList() {
        return query.selectFrom(product)
                .where(product.isDeleted.eq(Boolean.FALSE))
                .orderBy(product.productNum.asc())
                .fetch();
    }

    public Product getProduct(Long productNum) {
        return query.selectFrom(product)
                .where(product.isDeleted.eq(Boolean.FALSE).and(product.productNum.eq(productNum)))
                .fetchOne();
    }

    public void deleteLogical(Long productNum) {
        query.update(product)
                .set(product.isDeleted, Boolean.TRUE)
                .where(product.productNum.eq(productNum))
                .execute();
    }


    /**
     * Payment
     */
    /*재고 감소*/
    public  void subtractStock(Long productNum, Long paymentStock){
        query.update(product)
                .set(product.productStock, product.productStock.subtract(paymentStock))
                .where(product.productNum.eq(productNum))
                .execute();
    }

    /*재고 증가*/
    public void addStock(Long productNum, Long paymentStock){
        query.update(product)
                .set(product.productStock, product.productStock.add(paymentStock))
                .where(product.productNum.eq((long) productNum))
                .execute();
    }




}
