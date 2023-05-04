package com.example.demo.api.controller;

import com.example.demo.api.dto.member.MemberCreatedRequest;
import com.example.demo.api.dto.member.MemberCreatedResponse;
import com.example.demo.api.dto.member.MemberUpdatedRequest;
import com.example.demo.api.dto.member.MemberUpdatedResponse;
import com.example.demo.domain.member.Member;
import com.example.demo.exception.MemberNotFoundException;
import com.example.demo.service.interfaces.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api.com.solponge")
@Slf4j
public class MemberApiController {
    private final MemberService memberService;

    @PostMapping("/members")
    public ResponseEntity<MemberCreatedResponse> saveMember(@RequestBody MemberCreatedRequest request){
        Member member=new Member(
                request.getMemberId(),
                request.getMemberPwd(),
                request.getMemberName(),
                request.getMemberAddress(),
                request.getMemberEmail(),
                request.getMemberPhone()
                );
        Long id = memberService.join(member);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MemberCreatedResponse(id));

    }

    @PutMapping("/members/{memberNum}")
    public ResponseEntity<MemberUpdatedResponse> updateMember(
            @RequestBody @Validated MemberUpdatedRequest request,
            @PathVariable("memberNum") Long memberNum){
        Optional<Member> findMember = memberService.findByNum(memberNum);



        if (findMember.isPresent()){
            Member updateMember = findMember.orElseThrow(() -> new MemberNotFoundException("회원을 찾을 수 없습니다."));

            //회원 정보 업데이트
            updateMember(updateMember,
                    request.getMemberPwd(),
                    request.getMemberEmail1(),
                    request.getMemberEmail2(),
                    request.getMemberAddress1(),
                    request.getMemberAddress2(),
                    request.getMemberAddress3(),
                    request.getMemberPhone1(),
                    request.getMemberPhone2(),
                    request.getMemberPhone3());


            memberService.update(memberNum, updateMember);
            return ResponseEntity.ok(new MemberUpdatedResponse(updateMember.getMemberNum()));
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    /*문자열 합치기*/

    private void updateMember(Member loginMember,
                              String member_pwd,
                              String email1,
                              String email2,
                              String sample6_postcode,
                              String sample6_address,
                              String sample6_detailAddress,
                              String firstnum,
                              String secnum,
                              String thrnum) {
        String email = email1 + "@" + email2;
        String address = sample6_postcode + "/" + sample6_address + "/" + sample6_detailAddress;
        String phone = firstnum + "-" + secnum + "-" + thrnum;
        //멤버 수정정보 생성
        Member Member = new Member(member_pwd,address,email,phone);
        //멤버 업데이트
        memberService.update(loginMember.getMemberNum(),Member);
    }
}
