package com.vinisnzy.order_service.repository;

import com.vinisnzy.order_service.domain.OrderProduct;
import com.vinisnzy.order_service.domain.OrderProductId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductId> {
    void deleteByOrderId(Long orderId);
}
