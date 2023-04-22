package com.example.demo.repository.product;

import com.example.demo.domain.product.Product;
import com.example.demo.domain.utils.SearchCond;
import com.querydsl.core.QueryResults;
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

    public Page<Product> search(SearchCond cond, Pageable pageable) {
        QueryResults<Product> results = query.select(product)
                .from(product)
                .where(searchBySelect(cond.getSearchSelect(), cond.getSearchValue()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
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

    public List<Product> findNewTop8Products() {
        return query.selectFrom(product)
                .orderBy(product.productDate.desc())
                .limit(8)
                .fetch();
    }

    public List<Product> findPopularTop8Products() {
        return query.selectFrom(product)
                .orderBy(product.productVisit.desc())
                .limit(8)
                .fetch();
    }

    public List<Product> getProductList() {
        return query.selectFrom(product)
                .where(product.productGarbage.eq(1))
                .orderBy(product.productNum.asc())
                .fetch();
    }

    public Product getProduct(int productNum) {
        return query.selectFrom(product)
                .where(product.productGarbage.eq(1).and(product.productNum.eq((long) productNum)))
                .fetchOne();
    }

    public void deleteProduct(int productNum) {
        query.update(product)
                .set(product.productGarbage, 0)
                .where(product.productNum.eq((long) productNum))
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
