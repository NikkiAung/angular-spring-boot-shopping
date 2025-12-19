package com.coder.shop.dtos;

import java.util.ArrayList;
import java.util.List;

import com.coder.shop.models.OrderItem;

import lombok.Data;

@Data
public class OrderDto {
    private List<OrderItemDto> items;
    private double total;

    public double getTotal() {
        total = 0;
        for(OrderItemDto item : items) {
            total += item.getCount() * item.getProduct().getPrice();
        }
        return total;
    }

    public List<OrderItem> getOrderItems() {
        List<OrderItem> oItems = new ArrayList<OrderItem>();
        for (OrderItemDto item: items) {
            oItems.add(item.getOrderItem());
        }
        return oItems;
    }
}
