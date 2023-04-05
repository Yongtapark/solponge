package com.example.demo.config;

import com.example.demo.repository.member.MemberQueryRepository;
import com.example.demo.repository.member.MemberRepository;
import com.example.demo.repository.product.ProductQueryRepository;
import com.example.demo.repository.product.ProductRepository;
import com.example.demo.service.ProductServiceImpl;
import com.example.demo.service.interfaces.MemberService;
import com.example.demo.service.MemberServiceImpl;
import com.example.demo.service.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
@RequiredArgsConstructor
public class MainConfig {
    private final EntityManager em;

    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository,memberQueryRepository());
    }

    @Bean
    public ProductService productService(){
        return new ProductServiceImpl(productRepository,productQueryRepository());
    }

    @Bean
    public MemberQueryRepository memberQueryRepository(){
        return new MemberQueryRepository(em, memberRepository);
    }

    @Bean
    public ProductQueryRepository productQueryRepository(){
        return new ProductQueryRepository(em, productRepository);
    }




}
