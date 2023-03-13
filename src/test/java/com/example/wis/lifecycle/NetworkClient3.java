package com.example.wis.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient3 {

    private String url;

    public NetworkClient3() {
        System.out.println(this.getClass().getName()+" initialized");

    }

    public void setUrl(String url) {
        this.url = url;
    }

    //service 시작시 호출되는 method
    public void connect() {
        System.out.println(this.getClass().getName()+" connected");

    }

    public void call(String message) {
        System.out.println(this.getClass().getName() + " call " + this.url + ",message=" + message);

    }

    //service 종료시 호출되는 method
    public void disconnect() {
        System.out.println(this.getClass().getName()+" closed");
    }

    @PostConstruct
    public void init()  {
        System.out.println(this.getClass().getName()+" initializing...");

        connect();
        call("초기화 메세지");
    }

    @PreDestroy
    public void close()  {
        System.out.println(this.getClass().getName()+" closing...");
        disconnect();
    }
}
