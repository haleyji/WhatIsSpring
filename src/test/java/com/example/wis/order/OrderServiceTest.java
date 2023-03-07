package com.example.wis.order;

import com.example.wis.AppConfig;
import com.example.wis.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();

        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    @DisplayName("VIP회원은 1000원을 할인받는다")
    void test() {
        Member member = new Member(1L, "memberA", MemberGrade.VIP);
        memberService.signup(member);

        Order order = orderService.createOrder(member.getId(), "김영한 강의", 200000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(20000);
    }

}