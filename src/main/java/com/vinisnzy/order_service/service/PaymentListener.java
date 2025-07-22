package com.vinisnzy.order_service.service;

import com.vinisnzy.order_service.domain.Order;
import com.vinisnzy.order_service.enums.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentListener {

    @Autowired
    private OrderService orderService;

    @KafkaListener(topics = "payments.success", groupId = "order-service-group")
    public void onPaymentSuccess(String orderId) {
        Order order = orderService.findById(Long.parseLong(orderId));
        order.setStatus(OrderStatus.PAID);
        orderService.save(order);
    }

    @KafkaListener(topics = "payments.failure", groupId = "order-service-group")
    public void onPaymentFailure(String orderId) {
        Order order = orderService.findById(Long.parseLong(orderId));
        order.setStatus(OrderStatus.CANCELLED);
        orderService.save(order);
    }
}
