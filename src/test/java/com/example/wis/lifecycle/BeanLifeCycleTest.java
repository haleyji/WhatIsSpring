package com.example.wis.lifecycle;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    @DisplayName("InitializingBean, DisposableBean을 사용하여 Bean 생성 및 초기화한다")
    void test(){
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient bean = ac.getBean(NetworkClient.class);
        ac.close();
    }
    @Test
    @DisplayName("initMethod, destroyMethod 를 사용한다")
    void test1(){
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient2 bean = ac.getBean(NetworkClient2.class);
        ac.close();
    }
    @Test
    @DisplayName("PostCreate, PreDestroy 를 사용한다")
    void test2(){
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient3 bean = ac.getBean(NetworkClient3.class);
        ac.close();
    }


    @Configuration
    static class LifeCycleConfig{
//        @Bean
        public NetworkClient networkClient(){
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spirng.dev");
            return networkClient;
        }

//        @Bean(initMethod = "init", destroyMethod = "close")
        public NetworkClient2 networkClient2(){
            NetworkClient2 networkClient2 = new NetworkClient2();
            networkClient2.setUrl("http://localhost:8080/client");
            return networkClient2;
        }


        @Bean
        public NetworkClient3 networkClient3(){
            NetworkClient3 networkClient3 = new NetworkClient3();
            networkClient3.setUrl("http://localhost:8080/client");
            return networkClient3;
        }
    }
}
