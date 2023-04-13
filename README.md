# SOLPONGE 프로젝트 JPA 리펙토링

### 시작하기
1. `scarpData` 디렉토리로 이동합니다.
cd scarpData

markdown
Copy code

2. H2 DB에 `TableQuery`를 사용하여 테이블을 생성합니다.

3. `bookDataForH2.py`를 실행합니다.
python bookDataForH2.py

markdown
Copy code

4. `JobDataForH2.py`를 실행합니다.
python JobDataForH2.py

markdown
Copy code

5. `main.java`를 실행합니다.(서버포트=8081)
javac main.java
java main
### 사전 요구 사항
Java 11

SpringBoot

JPA

QueryDsl

H2 DB

### 변경점

##### 1. 디렉토리 구조 및 명칭 변경
![제목 없음](https://user-images.githubusercontent.com/91367204/231708363-cea85c7b-f97d-440d-89bc-705868a334bb.png)

##### 2. 도메인
![제목 없음2](https://user-images.githubusercontent.com/91367204/231709970-4c1b6b95-ef58-431d-a074-37a133ee6f7b.png)

##### 3.로직 단순화.
![1](https://user-images.githubusercontent.com/91367204/231712186-10ea61e7-d266-46ed-9e5d-8d61d33e7aee.PNG)


## 작성자
박용타
park young-ta
bjjtachan@gmail.com
