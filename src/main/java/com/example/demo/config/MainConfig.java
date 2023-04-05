package com.example.demo.config;

import com.example.demo.repository.MemberQueryRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.MemberService;
import com.example.demo.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
@RequiredArgsConstructor
public class MainConfig {
    private final EntityManager em;

    private final MemberRepository memberRepository;

    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository,memberQueryRepository());

    }

    @Bean
    public MemberQueryRepository memberQueryRepository(){
        return new MemberQueryRepository(em, memberRepository);
    }


}
