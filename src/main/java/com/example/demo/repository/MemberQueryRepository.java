package com.example.demo.repository;

import com.example.demo.domain.member.Member;
import com.example.demo.domain.member.QMember;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.example.demo.domain.member.QMember.*;


public class MemberQueryRepository {

    private final JPAQueryFactory query;

    public MemberQueryRepository(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    public List<Member> findAll(MemberSearchCond cond){
        return query.select(member)
                .from(member)
                .where(likeMemberName(cond.getMemberName())
                        ,likeMemberId(cond.getMemberId()))
                .fetch();

    }

    private BooleanExpression likeMemberName(String memberName){
        if (StringUtils.hasText(memberName)){
            return member.memberName.like("%" + memberName + "%");
        }
        return null;
    }

    private BooleanExpression likeMemberId(String memberId){
        if (StringUtils.hasText(memberId)){
            return member.memberId.like("%" + memberId + "%");
        }
        return null;
    }
}
