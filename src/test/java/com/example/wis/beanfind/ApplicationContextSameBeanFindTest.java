package com.example.wis.beanfind;

import com.example.wis.config.AppConfig;
import com.example.wis.discount.DiscountPolicy;
import com.example.wis.discount.FixDiscountPolicy;
import com.example.wis.discount.RateDiscountPolicy;
import com.example.wis.member.MemberRepository;
import com.example.wis.member.MemberService;
import com.example.wis.member.MemberServiceImpl;
import com.example.wis.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

public class ApplicationContextSameBeanFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfigCopy.class);
    @Test
    @DisplayName("타입으로 조회시, 같은 타입이 둘 이상 있으면 중복 오류가 발생한다")
    void findBeanByType(){
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class,
                ()->{
                    ac.getBean(MemberRepository.class);
                });
    }
    @Test
    @DisplayName("같은 타입의 빈이 두개 이상일시, 이름으로 조회하면 정상 작동한다")
    void findBeanByName(){
        Object bean = ac.getBean("memberRepository1", MemberRepository.class);
        org.assertj.core.api.Assertions.assertThat(bean).isInstanceOf(MemberRepository.class);
    }
    @Test
    @DisplayName("같은 타입의 빈을 모두 조회")
    void findBeansByType(){
        Map<String, MemberRepository> beans = ac.getBeansOfType(MemberRepository.class);
        for (String s : beans.keySet()) {
            System.out.println("key=" + s + ",value=" + beans.get(s));
        }
        org.assertj.core.api.Assertions.assertThat(beans.size()).isEqualTo(2);
    }


    @Configuration
    static class AppConfigCopy {
        @Bean
        public MemberRepository memberRepository1(){
            return new MemoryMemberRepository();
        }
        @Bean
        public MemberRepository memberRepository2(){
            return new MemoryMemberRepository();
        }
        @Bean
        public DiscountPolicy discountPolicy1(){
            return new FixDiscountPolicy();
        }
        @Bean
        public DiscountPolicy discountPolicy2(){
            return new RateDiscountPolicy();
        }
    }
}
