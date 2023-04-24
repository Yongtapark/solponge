package com.example.demo.streamTest;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@SpringBootTest
public class streamTest2 {
    @Test
    void test1to3(){
        /*예제:
        주어진 정수 리스트에서 모든 값의 제곱을 계산한 후, 결과를 오름차순으로 정렬하여 출력하세요.*/
        List<Integer> numbers = Arrays.asList(2, 3, 1, 5, 4);
        List<Integer> squaredNumbers = numbers.stream()
                .map(n -> n * n)
                .sorted()
                .collect(Collectors.toList());

        /*문제 1:
        주어진 정수 리스트에서 모든 값의 세제곱을 계산한 후, 결과를 내림차순으로 정렬하여 출력하세요.*/
        List<Integer> collect = numbers.stream()
                .map(n -> n * n * n)
                .sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        log.info("문제 1 : {}",collect);

       /* 문제 2:
        주어진 정수 리스트에서 각 값에 10을 더한 후, 결과를 내림차순으로 정렬하여 출력하세요.*/
        List<Integer> collect1 = numbers.stream()
                .map(n -> n + 10)
                .sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        log.info("문제 2 : {}",collect1);

       /* 예제:주어진 문자열 리스트에서 각 단어의 길이를 계산한 후, 길이가 짝수인 단어만 선택하여 출력하세요.*/
        List<String> words = Arrays.asList("apple", "banana", "cherry", "orange", "grape", "melon");
        List<String> evenLengthWords = words.stream()
                .filter(word -> word.length() % 2 == 0)
                .collect(Collectors.toList());

        /*문제 3:
        주어진 문자열 리스트에서 각 단어의 길이를 계산한 후, 길이가 홀수인 단어만 선택하여 출력하세요.*/
        List<String> collect2 = words.stream().filter(n -> n.length() % 2 != 0)
                .collect(Collectors.toList());

        log.info("문제 3 : {}",collect2);
    }

    @Test
    void test2(){
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> collect = numbers.stream()
                .filter(n -> n % 2 != 0)
                .collect(Collectors.toList());
        log.info("문제1:{}",collect);

        List<Integer> collect1 = numbers.stream()
                .filter(n -> n % 3 == 0)
                .collect(Collectors.toList());
        log.info("문제2:{}",collect1);

    List<String> words = Arrays.asList("apple", "banana", "cherry", "orange", "grapefruit", "watermelon");
        List<String> collect2 = words.stream().filter(n -> n.length() >= 5)
                .collect(Collectors.toList());

        log.info("문제3:{}",collect2);
    }

}
