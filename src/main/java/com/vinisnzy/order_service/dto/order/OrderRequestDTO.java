package com.vinisnzy.order_service.dto.order;

import com.vinisnzy.order_service.dto.product.OrderProductDTO;

import java.util.List;

public record OrderRequestDTO(
        String clientName,
        List<OrderProductDTO> items
) {
}
