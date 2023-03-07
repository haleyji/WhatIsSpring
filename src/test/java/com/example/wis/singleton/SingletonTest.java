package com.example.wis.singleton;

import com.example.wis.AppConfig;
import com.example.wis.member.MemberRepository;
import com.example.wis.member.MemberService;
import com.example.wis.member.MemberServiceImpl;
import com.example.wis.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

    @Test
    @DisplayName("AppConfig 를 초기화해서 불러온 memberSerivce 는 매번 새로운 객체이다")
    void test() {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        MemberService memberService1 = appConfig.memberService();

        Assertions.assertThat(memberService1).isNotEqualTo(memberService);
    }

    @Test
    @DisplayName("객체가 singleton으로 하나만 생성되었다")
    void test2() {
        //SingletonService singletonService = new SingletonService();
        //생성자 private 이라서 에러 발생

        SingletonService s1 = SingletonService.getInstance();
        SingletonService s2 = SingletonService.getInstance();

        //same 은 == 값이 저장된 메모리 주소 비교
        //equal 은 값 그 자체 비교

        Assertions.assertThat(s1).isEqualTo(s2);
        Assertions.assertThat(s1).isSameAs(s2);
    }

    @Test
    @DisplayName("spring container와 singleton")
    void test3() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService m1 = ac.getBean("memberService", MemberService.class);
        MemberService m2 = ac.getBean("memberService", MemberService.class);

        Assertions.assertThat(m1).isEqualTo(m2);
        Assertions.assertThat(m1).isSameAs(m2);

    }

    @Test
    @DisplayName("AppConfig에서 New로 memberRepository를 하지만 둘은 같다")
    void test4() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberServiceImpl m1 = ac.getBean(MemberServiceImpl.class);
        OrderServiceImpl o1 = ac.getBean(OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean(MemberRepository.class);

        Assertions.assertThat(m1.getMemberRepository()).isEqualTo(o1.getMemberRepository()).isEqualTo(memberRepository);
        //모두 같은 MemberRepository
    }

    @Test
    @DisplayName("@Configuration이 붙은 AppConfig.class는 내가 만든게 아니다")
    void test5(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        AppConfig bean = ac.getBean(AppConfig.class);
        System.out.println("bean=" + bean.getClass());
        //bean=class com.example.wis.AppConfig$$EnhancerBySpringCGLIB$$51070853
        //CGLIB 이라는 AppConfig 를 상속받는 Spring 이 만든 클래스를 스프링 빈으로 등록 -> singleton 이 보장되도록..

        /**
         * 위에서 memberRepository 를 가져올 때 spring container 에 등록되어 있으면, 있는 걸 반환
         * 없으면, new 로 만들어서 반환 -> singleton!
         */
    }
}
