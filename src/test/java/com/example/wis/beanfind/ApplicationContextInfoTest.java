package com.example.wis.beanfind;

import com.example.wis.config.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionDefaults;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class ApplicationContextInfoTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBeans(){
        String[] beanDefinitionName = ac.getBeanDefinitionNames();
        for (String s : beanDefinitionName) {
            Object bean = ac.getBean(s);
            System.out.println("bean name="+s+"|| bean="+bean);
        }
    }
    @Test
    @DisplayName("모든 애플리케이션 빈 출력하기")
    void findAllApplicationBeans(){
        String[] beanDefinitionName = ac.getBeanDefinitionNames();
        for (String s : beanDefinitionName) {
            Object bean = ac.getBean(s);
            if(ac.getBeanDefinition(s).getRole()==BeanDefinition.ROLE_APPLICATION){
                System.out.println(s);
            }

        }
    }
}
