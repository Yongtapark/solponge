package com.example.demo.domain.companyScrap;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCompanyScrap is a Querydsl query type for CompanyScrap
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCompanyScrap extends EntityPathBase<CompanyScrap> {

    private static final long serialVersionUID = -534412642L;

    public static final QCompanyScrap companyScrap = new QCompanyScrap("companyScrap");

    public final StringPath companyName = createString("companyName");

    public final NumberPath<Long> companyScrapNum = createNumber("companyScrapNum", Long.class);

    public final NumberPath<Long> memberNum = createNumber("memberNum", Long.class);

    public final DateTimePath<java.time.LocalDateTime> scrapTime = createDateTime("scrapTime", java.time.LocalDateTime.class);

    public QCompanyScrap(String variable) {
        super(CompanyScrap.class, forVariable(variable));
    }

    public QCompanyScrap(Path<? extends CompanyScrap> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCompanyScrap(PathMetadata metadata) {
        super(CompanyScrap.class, metadata);
    }

}

