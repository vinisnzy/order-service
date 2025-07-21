package com.vinisnzy.order_service.dto.order;

import com.vinisnzy.order_service.dto.product.OrderProductDTO;
import com.vinisnzy.order_service.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponseDTO (
        Long id,
        String clientName,
        LocalDateTime createdAt,
        BigDecimal subtotal,
        OrderStatus status,
        List<OrderProductDTO> items
) {
}
