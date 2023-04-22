package com.example.demo.repository.member;

import com.example.demo.domain.member.Member;
import com.example.demo.domain.utils.SearchCond;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

    private List<Member> searchMembers(SearchCond cond){
        return query.selectFrom(member)
                .where(searchBySelect((cond.getSearchSelect()), cond.getSearchValue()))
                .fetch();
    }

    private List<Member> paginateMembers(List<Member> members, Pageable pageable){
        int start = (int)pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), members.size());
        return members.subList(start,end);
    }

    public Page<Member> search(SearchCond cond, Pageable pageable) {
        List<Member> searchedMembers = searchMembers(cond);
        List<Member> paginatedMembers = paginateMembers(searchedMembers, pageable);
        return new PageImpl<>(paginatedMembers, pageable, searchedMembers.size());
    }


    /*public Page<Member> search(SearchCond cond, Pageable pageable){
        QueryResults<Member> results = query.select(member)
                .from(member)
                .where(searchBySelect(cond.getSearchSelect(), cond.getSearchValue()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(results.getResults(),pageable,results.getTotal());
    }*/

    private BooleanExpression searchBySelect(String searchSelect, String searchValue){
        if(StringUtils.hasText(searchSelect)&& StringUtils.hasText(searchValue)){
            switch (searchSelect){
                case "all":
                    return member.memberId.contains(searchValue)
                            .or(member.memberName.contains(searchValue));
                case "memberId":
                    return member.memberId.contains(searchValue);
                case"memberName":
                    return member.memberName.contains(searchValue);
            }
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
