package com.example.wis.discount;

import com.example.wis.member.Member;
import com.example.wis.member.MemberGrade;

public class FixDiscountPolicy implements DiscountPolicy{
    private int discountAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == MemberGrade.VIP) {
            return discountAmount;
        }
        return 0;
    }
}
