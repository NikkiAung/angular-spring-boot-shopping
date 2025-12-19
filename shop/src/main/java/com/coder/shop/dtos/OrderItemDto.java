package com.coder.shop.dtos;

import org.springframework.beans.factory.annotation.Autowired;

import com.coder.shop.models.OrderItem;
import com.coder.shop.models.Product;
import com.coder.shop.services.OrderService;
import com.coder.shop.services.ProductService;

import lombok.Data;

@Data
public class OrderItemDto {
    @Autowired
    private final ProductService productService;

    @Autowired
    private final OrderService orderService;

    private Long id;
    private int count;

    public Product getProduct() {
        return productService.get(id);
    }

    public OrderItem getOrderItem() {
        Product product = getProduct();

        OrderItem orderItem = new OrderItem();
        orderItem.setCount(count);
        orderItem.setPrice(product.getPrice());
        orderItem.setProduct(product);

        return orderService.addItem(orderItem);
    }
}
