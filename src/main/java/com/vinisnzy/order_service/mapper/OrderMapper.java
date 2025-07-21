package com.vinisnzy.order_service.mapper;

import com.vinisnzy.order_service.domain.Order;
import com.vinisnzy.order_service.domain.OrderProduct;
import com.vinisnzy.order_service.dto.order.OrderRequestDTO;
import com.vinisnzy.order_service.dto.order.OrderResponseDTO;
import com.vinisnzy.order_service.dto.product.OrderProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(target = "items", qualifiedByName = "orderProductListToDTO")
    OrderResponseDTO toDTO(Order order);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "subtotal", expression = "java(new java.math.BigDecimal(0))")
    @Mapping(target = "status", constant = "PENDING")
    @Mapping(target = "items", ignore = true)
    Order toEntity(OrderRequestDTO orderRequestDTO);

    @Mapping(target = "clientName", source = "clientName")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "subtotal", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "items", ignore = true)
    void update(OrderRequestDTO orderRequestDTO, @MappingTarget Order order);

    @Named("orderProductListToDTO")
    default List<OrderProductDTO> orderProductListToDTO(List<OrderProduct> items) {
        if (items == null) return List.of();
        return items.stream()
                .map(product -> new OrderProductDTO(
                        product.getProduct().getId(),
                        product.getQuantity()))
                .toList();
    }
}
