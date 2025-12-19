package com.coder.shop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coder.shop.models.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long>{

}
