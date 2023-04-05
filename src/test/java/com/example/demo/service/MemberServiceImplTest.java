package com.example.demo.service;

import com.example.demo.domain.member.Member;
import com.example.demo.repository.member.MemberSearchCond;
import com.example.demo.service.interfaces.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Slf4j
@SpringBootTest
@Transactional
class MemberServiceImplTest {

    @Autowired
    MemberService memberService;

    @Test
    void join() {
        //given
        Member member = new Member("id","pwd","name","address","email","phone");
        //when
        memberService.join(member);
        //then
        Member findMember = memberService.findByNo(member.getMemberNo()).get();
        Assertions.assertThat(member).isEqualTo(findMember);

    }

    @Test
    void update() {
        //given
        Member member = new Member("id","pwd","name","address","email","phone");
        Long memberNo = memberService.join(member);
        //when
        Member update = new Member("updatedPassword", "updatedAddress", "updatedEmail", "updatedPhone");
        memberService.update(member.getMemberNo(),update);
        //then
        Assertions.assertThat(memberService.findByNo(memberNo).get().getMemberAddress()).isEqualTo("updatedAddress");
        log.info("memberService.findByNo(memberNo).get()={}",memberService.findByNo(memberNo).get());
    }

    @Test
    void delete() {
        //given
        Member member = new Member("id","pwd","name","address","email","phone");
        Long memberNo = memberService.join(member);
        //when
        memberService.delete(memberNo);
        //then
        Assertions.assertThat(memberService.findByNo(memberNo)).isEmpty();
    }
    @Test
    void findAll() {
        //given
        Member user1 = new Member("id","pwd","name","address","email","phone");
        Member user2 = new Member("id2","pwd2","name2","address2","email2","phone2");
        memberService.join(user1);
        memberService.join(user2);
        //when
        List<Member> all = memberService.findAll();
        //then
        Assertions.assertThat(all).contains(user1,user2);
        log.info("memberService.findAll()={}",memberService.findAll());

    }

    @Test
    void findMembers() {
        Member user1 = new Member("id","pwd","name","address","email","phone");
        Member user2 = new Member("id2","pwd2","name2","address2","email2","phone2");

        log.info("memberService={}",memberService.getClass());
        memberService.join(user1);
        memberService.join(user2);

        //둘 다 없음 검증
        test(null,null,user1,user2);
        test("",null,user1,user2);

        //memberName 검증
        test("name",null,user1,user2);
        test("ame",null,user1,user2);
        test("name2",null,user2);

        //memberId 검증
        test(user1.getMemberName(),null,user1,user2);
        test(null,"i",user1,user2);
        test(null,"id2",user2);

        //둘 다 있음 검증
        test("name","id",user1,user2);
    }

    void test(String memberName, String memberId, Member... members) {
        MemberSearchCond cond = new MemberSearchCond(memberName, memberId);
        List<Member> result = memberService.findMembers(cond);
        Assertions.assertThat(result).containsExactlyInAnyOrder(members);
    }

    @Test
    void findMember1s() {
        //given
        Member user1 = new Member("id","pwd","name","address","email","phone");
        Member user2 = new Member("id2","pwd2","name2","address2","email2","phon2e");

        memberService.join(user1);
        memberService.join(user2);

        //when
        MemberSearchCond cond = new MemberSearchCond("", "id");
        log.info("cond={}",cond);

        List<Member> result = memberService.findMembers(cond);
        log.info("result={}", result);
        Assertions.assertThat(result).contains(user1, user2);
    }


}