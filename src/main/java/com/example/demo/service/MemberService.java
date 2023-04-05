package com.example.demo.service;


import com.example.demo.domain.member.Member;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.MemberSearchCond;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
