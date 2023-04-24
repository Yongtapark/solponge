package com.example.demo.streamTest;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class streamTest3 {
    @Test
    void test1(){
        /*예제 1:
        주어진 문자열 리스트에서 모든 문자열의 길이를 계산한 후 오름차순으로 정렬하세요.*/
        List<String> words = Arrays.asList("apple", "banana", "cherry", "orange", "grape", "melon");
        List<String> result = words.stream()
                .sorted(Comparator.comparing(String::length))
                .collect(Collectors.toList());

        /*문제 1:
        주어진 문자열 리스트에서 모든 소문자로 구성된 단어만 찾아 길이순으로 정렬하세요. (길이가 같은 경우, 사전순으로 정렬)*/
        List<String> collect = words.stream()
                .filter(n -> n.chars()
                        .allMatch(Character::isLowerCase))
                .sorted(Comparator.comparing(String::length))
                .collect(Collectors.toList());
        log.info("문1: {}",collect);

        class Student {
            String name;
            int score;

            public Student(String name, int score) {
                this.name = name;
                this.score = score;
            }

            public int getScore() {
                return score;
            }
        }

        List<Student> students = Arrays.asList(
                new Student("Alice", 85),
                new Student("Bob", 65),
                new Student("Charlie", 75),
                new Student("David", 55),
                new Student("Eve", 90)
        );
        /*문제 2:
        주어진 학생 객체 리스트에서, 성적이 60점 이상인 학생만 선택하고, 평균 점수를 계산하여 출력하세요.*/

        OptionalDouble average = students.stream().filter(n -> n.score >= 60)
                .mapToInt(n -> n.score)
                .average();

        log.info("문2: {}",average);


        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        /*문제 3:
        주어진 정수 리스트에서 가장 큰 값과 가장 작은 값을 찾아 출력하세요. (Optional 사용)
        */
        OptionalInt max = numbers.stream().mapToInt(n -> n.intValue())
                .max();
        OptionalInt min = numbers.stream().mapToInt(n -> n.intValue())
                .min();
        log.info("문3: {}, {}",max,min);




    }
}
