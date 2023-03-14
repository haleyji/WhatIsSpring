package com.example.wis.scope;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingletonWithPrototype {
    @Test
    @DisplayName("prototype bean 은 생성할 때마다 초기화 되서 addCount 가 소용없다")
    void test(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
        bean1.addCount();

        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);
        bean2.addCount();


        Assertions.assertThat(bean1.getCount()).isEqualTo(1).isEqualTo(bean2.getCount());
    }

    @Test
    @DisplayName("싱글톤 빈을 만들어서 prototype bean 을 autowired 하면 항상 같은 객체를 반환한다")
    void test1(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class, PrototypeBean.class);
        SingletonBean bean = ac.getBean(SingletonBean.class);
        int count1 = bean.logic();
        Assertions.assertThat(count1).isEqualTo(1);

        SingletonBean bean2 = ac.getBean(SingletonBean.class);
        int count2 = bean2.logic();
        Assertions.assertThat(count2).isEqualTo(2);
    }

    @Test
    @DisplayName("싱글톤 빈을 만들어서 prototype bean 을 prototype 방식으로 쓸 수 있다")
    void test2(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class, PrototypeBean.class);
        SingletonBean bean = ac.getBean(SingletonBean.class);
        int count1 = bean.logic2();
        Assertions.assertThat(count1).isEqualTo(1);

        SingletonBean bean2 = ac.getBean(SingletonBean.class);
        int count2 = bean2.logic2();
        Assertions.assertThat(count2).isEqualTo(1);
    }

    @Test
    @DisplayName("싱글톤 빈안에 provider 를 사용하여 prototype bean 을 prototype 방식으로 쓸 수 있다")
    void test3(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class, PrototypeBean.class);
        SingletonBean bean = ac.getBean(SingletonBean.class);
        int count1 = bean.logic2();
        Assertions.assertThat(count1).isEqualTo(1);

        SingletonBean bean2 = ac.getBean(SingletonBean.class);
        int count2 = bean2.logic2();
        Assertions.assertThat(count2).isEqualTo(1);
    }

    @Scope("singleton")
    @RequiredArgsConstructor
    static class SingletonBean{
        private final PrototypeBean prototypeBean;//생성 시점에 주입
        private final ApplicationContext applicationContext;

        @Autowired
        private ObjectProvider<PrototypeBean> prototypeProvider;

        public int logic(){
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
        public int logic2(){
            PrototypeBean pb = applicationContext.getBean(PrototypeBean.class);
            //spring container 에 종속적..
            pb.addCount();
            return pb.getCount();
        }
        public int logic3(){
            PrototypeBean pb = prototypeProvider.getObject();
            pb.addCount();
            return pb.getCount();
        }
    }
    @Scope("prototype")
    static class PrototypeBean {

        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean = " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean = " + this);
        }
    }
}
