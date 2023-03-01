package com.example.wis.discount;

import com.example.wis.member.Member;

public interface DiscountPolicy {


    /**
     *
     * @param member
     * @param price
     * @return 할인대상 금액
     */
    int discount(Member member, int price);

}
