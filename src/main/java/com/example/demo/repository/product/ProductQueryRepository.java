package com.example.demo.repository.product;

import com.example.demo.domain.product.Product;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.example.demo.domain.product.QProduct.*;

public class ProductQueryRepository {

    private final JPAQueryFactory query;
    private final ProductRepository productRepository;

    public ProductQueryRepository(EntityManager em, ProductRepository productRepository) {
        this.query = new JPAQueryFactory(em);
        this.productRepository = productRepository;
    }

    public List<Product> search(ProductSearchCond cond) {
        return query.select(product)
                .from(product)
                .where(searchBySelect(cond.getSearchSelect(), cond.getSearchValue()))
                .fetch();
    }

    private BooleanExpression searchBySelect(String searchSelect, String searchValue) {
        if (StringUtils.hasText(searchSelect) && StringUtils.hasText(searchValue)) {
            switch (searchSelect) {
                case "all":
                    return product.productTitle.contains(searchValue)
                            .or(product.productWriter.contains(searchValue));
                case "product_title":
                    return product.productTitle.contains(searchValue);
                case "product_writer":
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

   /* public Product getProduct(int productNum) {
        return query.selectFrom(product)
                .where(product.productGarbage.eq(1).and(product.productNum.eq((long) productNum)))
                .fetchOne();
    }

    public void deleteProduct(int productNum) {
        query.update(product)
                .set(product.productGarbage, 0)
                .where(product.productNum.eq((long) productNum))
                .execute();
    }*/




}
