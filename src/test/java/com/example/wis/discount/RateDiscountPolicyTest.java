package com.example.wis.discount;

import com.example.wis.member.Member;
import com.example.wis.member.MemberGrade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RateDiscountPolicyTest {

    DiscountPolicy discountPolicy;
    @BeforeEach
    public void beforeEach(){
        discountPolicy = new FixDiscountPolicy();
    }
    @Test
    @DisplayName("VIP는 10프로 할인이 적용되어야 한다")
    void test1(){
        //given
        Member member = new Member(1L, "vip", MemberGrade.VIP);
        //when
        int discountPrice = discountPolicy.discount(member, 10000);
        //then
        assertThat(discountPrice).isEqualTo(1000);
    }


    @Test
    @DisplayName("VIP가 아닌경우는 할인이 없다")
    void test(){
        //given
        Member member = new Member(1L, "basic", MemberGrade.BASIC);
        //when
        int discountPrice = discountPolicy.discount(member, 10000);
        //then
        assertThat(discountPrice).isEqualTo(0);
    }
}