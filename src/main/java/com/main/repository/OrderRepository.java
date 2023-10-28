package com.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.main.model.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity,Long>{
	@Query("SELECT o FROM OrderEntity o WHERE o.user.id = :userId AND (o.orderStatus = 'PLACED' OR o.orderStatus = 'CONFIRMED' OR o.orderStatus = 'SHIPPED' OR o.orderStatus = 'DELIVERED')")
	public List<OrderEntity> getUsersOrders(@Param("userId") Long userId);

}
