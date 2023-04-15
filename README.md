# SOLPONGE 프로젝트 JPA 리펙토링

### 웹 실행 전 사전준비 (데이터 세팅) !! 현재 '사람인_공고별_데이터_03.csv' 가 깃에 올라가지 않아 작업중 !!
1. `scarpData` 디렉토리로 이동합니다.
cd scarpData
2. H2 DB에 `TableQuery`를 사용하여 테이블을 생성합니다.
3. `bookDataForH2.py`를 실행합니다.
python bookDataForH2.py
4. `JobDataForH2.py`를 실행합니다.
python JobDataForH2.py
5. 이제 준비가 끝났습니다.

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

Yong-ta Park

bjjtachan@gmail.com
