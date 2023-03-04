package com.example.wis.singleton;

public class SingletonService {
    /**
     * singleton = 객체(클래스의 인스턴스)를 딱 하나만 생성하는 것
     * private 생성자를 이용해서 다른 곳에서 new 를 남발하지 못하게 함
     */
    private static final SingletonService instance = new SingletonService();

    private SingletonService() {

    }

    public static SingletonService getInstance() {
        return instance;
    }

}
