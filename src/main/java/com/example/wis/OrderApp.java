package com.example.wis;

import com.example.wis.config.AppConfig;
import com.example.wis.member.*;
import com.example.wis.order.Order;
import com.example.wis.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {

//        MemberService memberService = new AppConfig().memberService();
//        OrderService orderService = new AppConfig().orderService();

        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        memberService.signup(new Member(1L, "memberA", MemberGrade.VIP));

        Order order = orderService.createOrder(1L, "itemA", 15000);

        System.out.println(order.toString());


    }
}
