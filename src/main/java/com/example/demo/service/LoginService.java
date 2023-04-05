package com.example.demo.service;

import com.example.demo.domain.member.Member;
import com.example.demo.repository.member.MemberQueryRepository;
import com.example.demo.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private  final MemberRepository memberRepository;
    private final MemberQueryRepository queryRepository;

    /**
     *  return null 이면 실패
     */

    public Member login(String loginId, String loginPwd){
       /* Optional<Member> byMemberId = memberRepository.findByMemberId(loginId);
        Member member = byMemberId.get();
        if (member.getMEMBER_PWD().equals(loginPwd)){
            return member;
        }else{
            return null;
        }*/

        return queryRepository.findByMemberId(loginId)
                .filter(m->m.getMemberPwd().equals(loginPwd)).orElse(null);
    }



}
