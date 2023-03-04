package com.example.wis.config;

import com.example.wis.discount.DiscountPolicy;
import com.example.wis.discount.FixDiscountPolicy;
import com.example.wis.discount.RateDiscountPolicy;
import com.example.wis.member.MemberRepository;
import com.example.wis.member.MemberService;
import com.example.wis.member.MemberServiceImpl;
import com.example.wis.member.MemoryMemberRepository;
import com.example.wis.order.OrderService;
import com.example.wis.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public MemberService memberService() {
        System.out.println("AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }


    @Bean
    public OrderService orderService() {
        System.out.println("AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
}
