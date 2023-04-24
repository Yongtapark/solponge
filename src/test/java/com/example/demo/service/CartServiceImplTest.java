package com.example.demo.service;

import com.example.demo.domain.cart.Cart;
import com.example.demo.repository.cart.CartRepository;
import com.example.demo.service.interfaces.CartService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@Slf4j
@RequiredArgsConstructor
@Transactional
class CartServiceImplTest {

    @Autowired
    CartService cartService;
    @Autowired
    CartRepository cartRepository;
    @Test
    void getMyCart() {
        Cart cart = cartRepository.findById(3L).get();
        log.info("cart={}",cart.getMember().getMemberName());
        Cart cart1 = cartRepository.findByMemberMemberNum(3L).get();
        log.info("cart1={}",cart1.getMember().getMemberName());
    }

    @Test
    void streamTest(){
        /*문제 1:
        주어진 정수 리스트에서 짝수만 선택하고, 이들의 합을 계산한 다음 출력하세요.*/
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Integer reduce = numbers.stream().filter(n -> n % 2 == 0).reduce(0, Integer::sum);
        log.info("1번 문제 :{}",reduce);

       /* 문제 2:
        주어진 문자열 리스트에서 길이가 4 이상인 문자열만 선택하고, 이들을 대문자로 변환한 다음 출력하세요.*/
        List<String> words = Arrays.asList("Java", "Go", "C", "Rust", "Python", "Scala", "Kotlin");

        List<String> collect = words.stream().filter(word -> word.length() > 4)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        log.info("2번 문제 :{}",collect);

       /* 문제 3:
        주어진 학생 객체 리스트에서 평균 점수가 70점 이상인 학생의 이름을 출력하세요.*/
        @Getter
        @Setter
        class Student{
            String name;
            int score;

            public Student(String name, int score) {
                this.name = name;
                this.score = score;
            }
        }

        List<Student> objects = Arrays.asList(
                new Student("Alice",85),
                new Student("Bob",65),
                new Student("CharLie",75),
                new Student("David",55),
                new Student("Eve",90)
        );
        List<String> collect1 = objects.stream().
                filter(student -> student.score > 70).
                map(Student::getName).
                collect(Collectors.toList());

        log.info("3번문제 :{}",collect1);


    }
}