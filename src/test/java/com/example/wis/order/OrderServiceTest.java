package com.example.wis.order;

import com.example.wis.MemberApp;
import com.example.wis.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    private final MemberService memberService = new MemberServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();

    @Test
    void test(){
        Member member = new Member(1L, "memberA", MemberGrade.VIP);
        memberService.save(member);

        Order order = orderService.createOrder(member.getId(), "김영한 강의", 200000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}