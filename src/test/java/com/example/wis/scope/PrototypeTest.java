package com.example.wis.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrototypeTest {

    @Test
    @DisplayName("스코프를 프로토타입으로 할 경우, 두개의 빈의 값은 다르다")
    void test() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        System.out.println("find bean 1");
        PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find bean 2");
        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);

        Assertions.assertThat(bean1).isNotSameAs(bean2);

        bean1.destroy();
        bean2.destroy();
        ac.close();
    }

    @Scope("prototype")
    static class PrototypeBean {

        @PostConstruct
        public void init() {
            System.out.println("Prototype Bean init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("Prototype Bean destroy");
        }
    }
}
