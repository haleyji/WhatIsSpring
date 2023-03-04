package com.example.wis.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    @DisplayName("")
    void test() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService s1 = ac.getBean(StatefulService.class);
        StatefulService s2 = ac.getBean(StatefulService.class);

        //Thread1
        s1.order("user A", 10000);

        //Thread2
        s2.order("user B", 20000);

        Assertions.assertThat(s1.getPrice()).isNotEqualTo(10000);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}