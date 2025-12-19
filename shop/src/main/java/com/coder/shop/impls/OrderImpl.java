package com.coder.shop.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coder.shop.exceptions.OrderNotFoundException;
import com.coder.shop.models.Order;
import com.coder.shop.models.OrderItem;
import com.coder.shop.repos.OrderItemRepo;
import com.coder.shop.repos.OrderRepo;
import com.coder.shop.services.OrderService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderImpl implements OrderService {
    @Autowired
    private final OrderRepo orderRepo;

    @Autowired
    private final OrderItemRepo orderItemRepo;

    @Override
    public void add(Order order) {
        orderRepo.save(order);
    }

    @Override
    public Order get(Long id) {
        return orderRepo.findById(id).orElseThrow(()-> new OrderNotFoundException("No order with that id"));
    }

    @Override
    public List<Order> all() {
        // to find all orders by req user id
        return orderRepo.findAll();
    }

    @Override
    public OrderItem addItem(OrderItem item) {
        return orderItemRepo.save(item);
    }
}
