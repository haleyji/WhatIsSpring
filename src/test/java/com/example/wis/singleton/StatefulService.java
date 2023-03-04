package com.example.wis.singleton;
public class StatefulService {

    /**
     * 특정 클라이언트에 의존적인 필드 있으면 안됨.
     * 특정 클라이언트가 값 변경할 수 있는 필드가 있으면 안됨.
     * 읽기만 가능해야함 (가급적)
     * 필드 대신에 자바에서 공유되지 않는, 지역변수, 파라미터, ThreadLocal 등을 사용해야 함
     */
    private int price;

    public void order(String name, int price) {
        System.out.println("name=" + name + ", price=" + price);
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
