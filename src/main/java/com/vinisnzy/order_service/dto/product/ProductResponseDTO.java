package com.vinisnzy.order_service.dto.product;

public record ProductResponseDTO(
        Long id,
        String name,
        Double price
) {
}
