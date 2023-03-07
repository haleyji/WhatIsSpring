package com.example.wis.member;

import com.example.wis.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberServiceTest {
    MemberService memberService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }
    @Test
    @DisplayName("회원등급이 VIP인 memberA를 생성한다")
    void test(){
        //given
        Member member = new Member(1L, "memberA", MemberGrade.VIP);

        //when
        memberService.signup(member);
        Member findMember = memberService.getById(1L);

        //then
        Assertions.assertThat(member).isEqualTo(findMember);

    }

}