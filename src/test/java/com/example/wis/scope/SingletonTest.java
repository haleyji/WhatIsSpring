package com.example.wis.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingletonTest {

    @Test
    @DisplayName("스코프를 싱글톤이라고 할 경우 싱글톤으로 생성된다")
    void test(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);
        SingletonBean s1 = ac.getBean(SingletonBean.class);
        SingletonBean s2 = ac.getBean(SingletonBean.class);
        Assertions.assertThat(s1).isEqualTo(s2);

        ac.close();
    }
    @Scope("singleton")
    static class SingletonBean{
        @PostConstruct
        public void init(){
            System.out.println("Singleton Bean Init");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("Singleton Bean Destroy");
        }
    }
}
