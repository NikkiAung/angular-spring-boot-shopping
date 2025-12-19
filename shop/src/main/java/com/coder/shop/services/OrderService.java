package com.coder.shop.services;

import java.util.List;

import com.coder.shop.models.Order;
import com.coder.shop.models.OrderItem;

public interface OrderService {
    void add(Order order);

    OrderItem addItem(OrderItem item);

    Order get(Long id);

    List<Order> all();
}
