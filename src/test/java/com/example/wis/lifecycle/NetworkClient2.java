package com.example.wis.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient2 {

    private String url;

    public NetworkClient2() {
        System.out.println("network client 2");
        System.out.println("생성자 호출 , url = " + this.url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //service 시작시 호출되는 method
    public void connect() {
        System.out.println("network client 2");
        System.out.println("connect : " + this.url);
    }

    public void call(String message) {
        System.out.println("network client 2");
        System.out.println("call : " + this.url + ", message = " + message);
    }

    //service 종료시 호출되는 method
    public void disconnect() {
        System.out.println("network client 2");
        System.out.println("close : " + this.url);
    }

    public void init()  {
        System.out.println("network client 2");
        System.out.println(">>>init");
        connect();
        call("초기화 메세지");
    }

    public void close()  {
        System.out.println("network client 2");
        System.out.println(">>>>close");
        disconnect();
    }
}
