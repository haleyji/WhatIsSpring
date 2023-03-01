package com.example.wis;

import com.example.wis.member.*;
import com.example.wis.order.Order;
import com.example.wis.order.OrderService;
import com.example.wis.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        memberService.save(new Member(1L, "memberA", MemberGrade.VIP));


        Order order = orderService.createOrder(1L, "itemA", 15000);

        System.out.println(order.toString());


    }
}
