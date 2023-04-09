package com.example.demo.domain.member;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberJoinForm is a Querydsl query type for MemberJoinForm
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberJoinForm extends EntityPathBase<MemberJoinForm> {

    private static final long serialVersionUID = 2066162316L;

    public static final QMemberJoinForm memberJoinForm = new QMemberJoinForm("memberJoinForm");

    public final StringPath memberAddress = createString("memberAddress");

    public final StringPath memberEmail = createString("memberEmail");

    public final EnumPath<Grade> memberGrade = createEnum("memberGrade", Grade.class);

    public final StringPath memberId = createString("memberId");

    public final StringPath memberName = createString("memberName");

    public final NumberPath<Long> memberNo = createNumber("memberNo", Long.class);

    public final StringPath memberPhone = createString("memberPhone");

    public final StringPath memberPwd = createString("memberPwd");

    public QMemberJoinForm(String variable) {
        super(MemberJoinForm.class, forVariable(variable));
    }

    public QMemberJoinForm(Path<? extends MemberJoinForm> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberJoinForm(PathMetadata metadata) {
        super(MemberJoinForm.class, metadata);
    }

}

