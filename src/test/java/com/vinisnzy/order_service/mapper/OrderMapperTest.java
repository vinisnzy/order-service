package com.vinisnzy.order_service.mapper;

import com.vinisnzy.order_service.domain.Order;
import com.vinisnzy.order_service.dto.order.OrderRequestDTO;
import com.vinisnzy.order_service.dto.order.OrderResponseDTO;
import com.vinisnzy.order_service.enums.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OrderMapperTest {

    private Order order;
    private OrderRequestDTO orderRequestDTO;

    @BeforeEach
    void setUp() {
        order = new Order();
        order.setId(1L);
        order.setClientName("John Doe");
        order.setCreatedAt(LocalDateTime.now());
        order.setSubtotal(new BigDecimal("100.00"));
        order.setStatus(OrderStatus.PENDING);
        order.setItems(null);

        orderRequestDTO = new OrderRequestDTO(
                order.getClientName(),
                null
        );
    }

    @Test
    void shouldConvertOrderToOrderResponseDTO_WhenOrderIsValid() {
        OrderResponseDTO result = OrderMapper.INSTANCE.toDTO(order);

        assertNotNull(result);
        assertEquals(order.getId(), result.id());
        assertEquals(order.getClientName(), result.clientName());
        assertEquals(order.getCreatedAt(), result.createdAt());
        assertEquals(order.getSubtotal(), result.subtotal());
        assertEquals(order.getStatus(), result.status());
        assertNull(result.items());
    }

    @Test
    void shouldConvertOrderRequestDTOToOrder_WhenDTOIsValid() {
        Order result = OrderMapper.INSTANCE.toEntity(orderRequestDTO);

        assertNotNull(result);
        assertEquals(orderRequestDTO.clientName(), result.getClientName());
        assertEquals(new BigDecimal("0"), result.getSubtotal());
        assertEquals(OrderStatus.PENDING, result.getStatus());
        assertEquals(0, result.getItems().size());
    }

    @Test
    void shouldConvertOrderListToOrderResponseDTOList_WhenOrderListIsValid() {
        List<Order> orders = List.of(order);
        List<OrderResponseDTO> result = new ArrayList<>();
        result.add(OrderMapper.INSTANCE.toDTO(orders.getFirst()));

        OrderResponseDTO firstResult = result.getFirst();
        assertEquals(1, result.size());
        assertEquals(order.getId(), firstResult.id());
        assertEquals(order.getClientName(), firstResult.clientName());
        assertEquals(order.getCreatedAt(), firstResult.createdAt());
        assertEquals(order.getSubtotal(), firstResult.subtotal());
        assertEquals(order.getStatus(), firstResult.status());
        assertEquals(firstResult.items(), List.of());
    }
}