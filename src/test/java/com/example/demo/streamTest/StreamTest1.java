package com.example.demo.streamTest;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.type.descriptor.java.IntegerTypeDescriptor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
@Slf4j
public class StreamTest1 {
    @Test
    void streamTest1to3(){
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

    @Test
    void streamTest4to6(){
        /*문제 4:
        주어진 정수 리스트에서 모든 값의 제곱근을 계산한 후, 결과를 내림차순으로 정렬하여 출력하세요.*/
        List<Integer> numbers = Arrays.asList(64, 49, 25, 16, 81, 9, 4);
        List<Double> collect = numbers.stream().
                map(Math::sqrt).
                sorted(Comparator.reverseOrder())
                        .collect(Collectors.toList());
        log.info("4번 : {}",collect);

       /* 문제 5:
        주어진 문자열 리스트에서 각 단어의 길이를 계산하고, 길이가 짝수인 단어만 선택하여 출력하세요.*/
        List<String> words = Arrays.asList("apple", "banana", "cherry", "orange", "grape", "melon");
        List<String> collect1 = words.stream()
                .filter(word -> word.length() % 2 == 0)
                .collect(Collectors.toList());
        log.info("5번 : {}",collect1);

        /*문제 6:
        주어진 학생 객체 리스트에서 성적이 60점 이상인 학생만 선택하고, 이름을 알파벳 오름차순으로 정렬하여 출력하세요.*/
        class Student {
            String name;
            int score;

            public Student(String name, int score) {
                this.name = name;
                this.score = score;
            }

            public String getName() {
                return name;
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
        List<String> collect2 = students.stream()
                .filter(student -> student.score > 60)
                .map(student -> student.getName()).sorted().collect(Collectors.toList());
        log.info("6번 : {}",collect2);

    }

    @Test
    void streamTest7to10(){
        /*문제 7:
        주어진 정수 리스트에서 모든 값의 제곱을 계산한 후, 결과를 오름차순으로 정렬하여 출력하세요.*/
        List<Integer> numbers = Arrays.asList(2, 3, 1, 5, 4);
        List<Integer> collect = numbers.stream().map(n -> n * n).sorted().collect(Collectors.toList());
        log.info("7번 : {}",collect);

        /*문제 8:
        주어진 문자열 리스트에서 가장 긴 단어를 찾아 출력하세요.*/
        List<String> words = Arrays.asList("apple", "banana", "cherry", "orange", "grape", "melon");
        //가장 긴 단어의 길이를 찾는다
        int maxLength = words.stream()
                .mapToInt(String::length)
                .max()
                .orElse(0);

        List<String> collect1 = words.stream()
                .filter(n -> n.length() == maxLength)
                .collect(Collectors.toList());

        log.info("8번 : {}",collect1);

        /*문제 9:
        주어진 학생 객체 리스트에서 성적이 가장 높은 학생의 이름을 출력하세요.*/
        @Data
        class Student {
            String name;
            int score;

            public Student(String name, int score) {
                this.name = name;
                this.score = score;
            }

            public String getName() {
                return name;
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
        Student student = students.stream().
                max(Comparator.comparingInt(Student::getScore)).get();


        log.info("9번 : {}",student);


    }
}
