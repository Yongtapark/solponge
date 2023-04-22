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
    /**
     * 검색
     */

    private List<Member> paginateMembers(List<Member> members, Pageable pageable){
        int start = (int)pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), members.size());
        return members.subList(start,end);
    }
    private List<Member> searchMembers(SearchCond cond){
        return query.selectFrom(member)
                .where(member.isDeleted.eq(Boolean.FALSE).and(searchBySelect((cond.getSearchSelect()), cond.getSearchValue())))
                .fetch();
    }


    public Page<Member> search(SearchCond cond, Pageable pageable) {
        List<Member> searchedMembers = searchMembers(cond);
        List<Member> paginatedMembers = paginateMembers(searchedMembers, pageable);
        return new PageImpl<>(paginatedMembers, pageable, searchedMembers.size());
    }


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

    public void deleteLogical(Long memberNum){
        query.update(member)
                .set(member.isDeleted,Boolean.TRUE)
                .where(member.memberNum.eq(memberNum))
                .execute();
    }

    public Optional<Member> findByMemberId(String memberId){
        return memberRepository.findAll().stream()
                .filter(member -> member.getMemberId().equals(memberId)).findAny();
    }
}
