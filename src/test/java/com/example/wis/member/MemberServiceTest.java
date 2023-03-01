package com.example.wis.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService = new MemberServiceImpl();
    @Test
    void test(){
        //given
        Member member = new Member(1L, "memberA", MemberGrade.VIP);

        //when
        memberService.save(member);
        Member findMember = memberService.findById(1L);

        //then
        Assertions.assertThat(member).isEqualTo(findMember);

    }

}