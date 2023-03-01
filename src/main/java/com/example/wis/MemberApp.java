package com.example.wis;

import com.example.wis.member.Member;
import com.example.wis.member.MemberGrade;
import com.example.wis.member.MemberService;
import com.example.wis.member.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        Member memberA = new Member(1L, "memberA", MemberGrade.VIP);
        memberService.save(memberA);

        Member findMember = memberService.findById(1L);
        System.out.println("new member=" + memberA.getName());
        System.out.println("find member=" + findMember.getName());
    }
}
