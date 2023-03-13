package com.example.wis.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient implements InitializingBean, DisposableBean {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출 , url = " + this.url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //service 시작시 호출되는 method
    public void connect() {
        System.out.println("connect : " + this.url);
    }

    public void call(String message) {
        System.out.println("call : " + this.url + ", message = " + message);
    }

    //service 종료시 호출되는 method
    public void disconnect() {
        System.out.println("close : " + this.url);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(">>>after properties set");
        connect();
        call("초기화 메세지");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println(">>>>destroy");
        disconnect();
    }
}
