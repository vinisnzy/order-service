package com.vinisnzy.order_service.service;

import com.vinisnzy.order_service.domain.Order;
import com.vinisnzy.order_service.dto.order.OrderRequestDTO;
import com.vinisnzy.order_service.dto.order.OrderResponseDTO;
import com.vinisnzy.order_service.exceptions.ResourceNotFoundException;
import com.vinisnzy.order_service.mapper.OrderMapper;
import com.vinisnzy.order_service.repository.OrderRepository;
import com.vinisnzy.order_service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper mapper;

    public OrderResponseDTO createOrder(OrderRequestDTO dto) {
        Order order = mapper.orderRequestDTOToOrder(dto);
        order = orderRepository.save(order);
        return mapper.orderToOrderResponseDTO(order);
    }

    public List<OrderResponseDTO> getAllOrders() {
        List<Order> list = orderRepository.findAll();
        return list.stream()
                .map(mapper::orderToOrderResponseDTO)
                .toList();
    }

    public OrderResponseDTO getOrderById(Long id) {
        Order order = findById(id);
        return mapper.orderToOrderResponseDTO(order);
    }

    public void addProductToOrder(Long orderId, Long productId) {
        // Logic to add a product to an order
    }

    public OrderResponseDTO updateOrder(Long id, OrderRequestDTO dto) {
        Order order = findById(id);
        mapper.updateOrderFromRequestDTO(dto, order);
        order = orderRepository.save(order);
        return mapper.orderToOrderResponseDTO(order);
    }

    public void deleteOrder(Long id) {
        Order order = findById(id);
        orderRepository.delete(order);
    }

    private Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
    }
}
