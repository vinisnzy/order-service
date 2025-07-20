package com.vinisnzy.order_service.repository;

import com.vinisnzy.order_service.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
