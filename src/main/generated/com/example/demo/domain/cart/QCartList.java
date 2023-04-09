package com.example.demo.domain.cart;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCartList is a Querydsl query type for CartList
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCartList extends EntityPathBase<CartList> {

    private static final long serialVersionUID = -987143716L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCartList cartList = new QCartList("cartList");

    public final StringPath cartDate = createString("cartDate");

    public final NumberPath<Integer> cartItemStock = createNumber("cartItemStock", Integer.class);

    public final NumberPath<Integer> cartNum = createNumber("cartNum", Integer.class);

    public final com.example.demo.domain.member.QMember member;

    public final com.example.demo.domain.product.QProduct product;

    public QCartList(String variable) {
        this(CartList.class, forVariable(variable), INITS);
    }

    public QCartList(Path<? extends CartList> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCartList(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCartList(PathMetadata metadata, PathInits inits) {
        this(CartList.class, metadata, inits);
    }

    public QCartList(Class<? extends CartList> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.example.demo.domain.member.QMember(forProperty("member")) : null;
        this.product = inits.isInitialized("product") ? new com.example.demo.domain.product.QProduct(forProperty("product")) : null;
    }

}

