package com.example.wis.order;

import com.example.wis.discount.DiscountPolicy;
import com.example.wis.discount.FixDiscountPolicy;
import com.example.wis.discount.RateDiscountPolicy;
import com.example.wis.member.Member;
import com.example.wis.member.MemberRepository;
import com.example.wis.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
