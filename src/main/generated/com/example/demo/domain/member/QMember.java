package com.example.demo.domain.member;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 1118978910L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMember member = new QMember("member1");

    public final com.example.demo.domain.cart.QCart cart;

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final StringPath memberAddress = createString("memberAddress");

    public final DateTimePath<java.time.LocalDateTime> memberDate = createDateTime("memberDate", java.time.LocalDateTime.class);

    public final StringPath memberEmail = createString("memberEmail");

    public final EnumPath<Grade> memberGrade = createEnum("memberGrade", Grade.class);

    public final StringPath memberId = createString("memberId");

    public final StringPath memberName = createString("memberName");

    public final NumberPath<Long> memberNum = createNumber("memberNum", Long.class);

    public final StringPath memberPhone = createString("memberPhone");

    public final StringPath memberPwd = createString("memberPwd");

    public QMember(String variable) {
        this(Member.class, forVariable(variable), INITS);
    }

    public QMember(Path<? extends Member> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMember(PathMetadata metadata, PathInits inits) {
        this(Member.class, metadata, inits);
    }

    public QMember(Class<? extends Member> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cart = inits.isInitialized("cart") ? new com.example.demo.domain.cart.QCart(forProperty("cart"), inits.get("cart")) : null;
    }

}

