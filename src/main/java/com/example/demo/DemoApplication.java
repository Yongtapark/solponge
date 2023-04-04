package com.example.demo;

import com.example.demo.config.MainConfig;
import com.example.demo.repository.MemberRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@Import(MainConfig.class)
@SpringBootApplication
public class DemoApplication {


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	/*@Bean
	@Profile("local")
	public TestDataInit testDataInit(MemberRepository memberRepository) {
		return new TestDataInit(memberRepository);
	}*/

}
