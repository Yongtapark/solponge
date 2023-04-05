package com.example.demo.service;

import com.example.demo.domain.member.Member;
import com.example.demo.repository.MemberQueryRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.MemberSearchCond;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberQueryRepository memberQueryRepository;

    @Override
    public Long join(Member member) {
        return memberRepository.save(member).getMemberNo();
    }

    @Override
    public void update(Long memberNo, Member member) {
        Member findmember = memberRepository.findById(memberNo).orElseThrow();//?
        findmember.setMemberPwd(member.getMemberPwd());
        findmember.setMemberPhone(member.getMemberPhone());
        findmember.setMemberAddress(member.getMemberAddress());
        findmember.setMemberEmail(member.getMemberEmail());
    }

    @Override
    public void delete(Long memberNo) {
        Member deleteMember = findByNo(memberNo).get();
        memberRepository.delete(deleteMember);
    }

    @Override
    public Optional<Member> findByNo(Long memberNo) {
        return memberRepository.findById(memberNo);
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public List<Member> findMembers(MemberSearchCond cond) {
        return memberQueryRepository.findAll(cond);
    }


    public Optional<Member> findByMemberId(String memberId) {
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
