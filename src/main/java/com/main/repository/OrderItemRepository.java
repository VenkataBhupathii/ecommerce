package com.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long>{

}
