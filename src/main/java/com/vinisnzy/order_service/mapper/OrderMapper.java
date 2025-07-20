package com.vinisnzy.order_service.mapper;

import com.vinisnzy.order_service.domain.Order;
import com.vinisnzy.order_service.dto.order.OrderRequestDTO;
import com.vinisnzy.order_service.dto.order.OrderResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderResponseDTO orderToOrderResponseDTO(Order order);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "subtotal", expression = "java(new java.math.BigDecimal(0))")
    @Mapping(target = "status", constant = "PENDING")
    @Mapping(target = "products", ignore = true)
    Order orderRequestDTOToOrder(OrderRequestDTO orderRequestDTO);

    @Mapping(target = "clientName", source = "orderRequestDTO.clientName")
    void updateOrderFromRequestDTO(OrderRequestDTO orderRequestDTO, @MappingTarget Order order);
}
