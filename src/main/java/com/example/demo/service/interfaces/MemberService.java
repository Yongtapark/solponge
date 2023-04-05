package com.example.demo.service.interfaces;


import com.example.demo.domain.member.Member;
import com.example.demo.repository.member.MemberSearchCond;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    Long join(Member member);
    void update(Long memberNo, Member member);
    void delete(Long memberNo);//탈퇴
    Optional<Member> findByNo(Long memberNo);

    List<Member> findAll();

    List<Member> findMembers(MemberSearchCond cond);


}
