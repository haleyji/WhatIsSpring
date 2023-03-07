package com.example.wis;

import com.example.wis.member.Member;
import com.example.wis.member.MemberGrade;
import com.example.wis.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
//        MemberService memberService = new AppConfig().memberService();
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        Member memberA = new Member(1L, "memberA", MemberGrade.VIP);
        memberService.signup(memberA);

        Member findMember = memberService.getById(1L);
        System.out.println("new member=" + memberA.getName());
        System.out.println("find member=" + findMember.getName());
    }
}
