package com.example.wis.order;

import org.springframework.stereotype.Service;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
