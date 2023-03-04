package com.example.wis.beanfind;

import com.example.wis.config.AppConfig;
import com.example.wis.discount.DiscountPolicy;
import com.example.wis.discount.FixDiscountPolicy;
import com.example.wis.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

public class ApplicationContextExtendsClass {

    AnnotationConfigApplicationContext ac =
            new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면 중복 오류가 발생한다")
    void test() {
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class, () -> {
            DiscountPolicy dp = ac.getBean(DiscountPolicy.class);
        });
    }

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면 빈 이름으로 조회한다")
    void test1() {
        DiscountPolicy dc = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        org.assertj.core.api.Assertions.assertThat(dc).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 전부 다 조회")
    void test2() {
        Map<String, DiscountPolicy> beans = ac.getBeansOfType(DiscountPolicy.class);
        org.assertj.core.api.Assertions.assertThat(beans.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("부모 타입으로 전부 다 조회")
    void test3() {
        Map<String, Object> beans = ac.getBeansOfType(Object.class);
        org.assertj.core.api.Assertions.assertThat(beans.size() > 2);
    }

    @Configuration
    static class TestConfig {

        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }
    }
}
