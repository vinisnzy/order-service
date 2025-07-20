package com.vinisnzy.order_service.dto.order;

import com.vinisnzy.order_service.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderResponseDTO(
        Long id,
        String clientName,
        LocalDateTime createdAt,
        BigDecimal subtotal,
        OrderStatus status
) {
}
