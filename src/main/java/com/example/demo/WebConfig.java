package com.example.demo;

import com.example.demo.domain.member.login.interceptor.AdminLoginCheckInterceptor;
import com.example.demo.domain.member.login.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {




    public void addInterceptors(InterceptorRegistry registry){

//어드민 여부 확인

        registry.addInterceptor(new AdminLoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/com.solponge/admin/**");

//회원 여부 확인

        registry.addInterceptor(new LoginCheckInterceptor())
                .order(2)
                .addPathPatterns("/**")
//체크 예외 경로

                .excludePathPatterns(
                        "/api.com.solponge/**",
                        "/com.solponge/main",
                        "/com.solponge/join",
                        "/com.solponge/login",
                        "/com.solponge/product/**",
                        "/com.solponge/productList/**",
                        "/com.solponge/jobInfo/**",
                        "/css/**",
                        "/img/**",
                        "/js/**"

                );
        
    }
}
