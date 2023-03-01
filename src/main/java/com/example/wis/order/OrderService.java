package com.example.wis.order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
