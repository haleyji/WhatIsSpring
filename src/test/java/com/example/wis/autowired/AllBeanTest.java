package com.example.wis.autowired;

import com.example.wis.AppConfig;
import com.example.wis.AutoAppConfig;
import com.example.wis.discount.DiscountPolicy;
import com.example.wis.member.Member;
import com.example.wis.member.MemberGrade;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

public class AllBeanTest {

    @Test
    @DisplayName("PolicyMap에서 fixDiscountPolicy를 key 값으로 하여 할인정책을 가져오면 1000원할인이 된다")
    void test(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "member A", MemberGrade.VIP);

        int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");
        Assertions.assertThat(discountService).isInstanceOf(DiscountService.class);
        Assertions.assertThat(discountPrice).isEqualTo(1000);

    }
    @Test
    @DisplayName("PolicyMap에서 rateDiscountPolicy를 key 값으로 하여 할인정책을 가져오면 10퍼센트 할인이 된다")
    void test1(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "member A", MemberGrade.VIP);

        int discountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");
        Assertions.assertThat(discountService).isInstanceOf(DiscountService.class);
        Assertions.assertThat(discountPrice).isEqualTo(2000);

    }


    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policyList;


        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policyList) {
            this.policyMap = policyMap;
            this.policyList = policyList;
            System.out.println("policy map>>>>"+policyMap);
            System.out.println("policy list>>>>"+policyList);
        }

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member, price);
        }
    }
}
