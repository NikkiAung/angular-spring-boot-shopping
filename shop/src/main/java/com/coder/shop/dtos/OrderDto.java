package com.coder.shop.dtos;

import java.util.List;

import lombok.Data;

@Data
public class OrderDto {
    private List<OrderItemDto> items;
    private double total;
}
