package com.example.wis.discount;

import com.example.wis.annotation.MainDiscountPolicy;
import com.example.wis.member.Member;
import com.example.wis.member.MemberGrade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy {
    private int discountRate = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == MemberGrade.VIP) {
            return price * discountRate / 100;
        }
        return 0;
    }
}
