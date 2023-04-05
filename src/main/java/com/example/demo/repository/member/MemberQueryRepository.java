package com.example.demo.repository.member;

import com.example.demo.domain.member.Member;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.example.demo.domain.member.QMember.*;


public class MemberQueryRepository {

    private final JPAQueryFactory query;
    private final MemberRepository memberRepository;

    public MemberQueryRepository(EntityManager em, MemberRepository memberRepository) {
        this.query = new JPAQueryFactory(em);
        this.memberRepository = memberRepository;
    }

    public List<Member> search(MemberSearchCond cond){
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

    public Optional<Member> findByMemberId(String memberId){
        /*List<Member> all = memberFindAll();
        for (Member member : all) {

            if(member.getMEMBER_ID().equals(memberId)){
                return Optional.of(member);
            }
        }

        return Optional.empty();*/

        return memberRepository.findAll().stream()
                .filter(member -> member.getMemberId().equals(memberId)).findAny();
    }
}
