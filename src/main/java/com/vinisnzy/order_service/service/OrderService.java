package com.vinisnzy.order_service.service;

import com.vinisnzy.order_service.domain.Order;
import com.vinisnzy.order_service.domain.OrderProduct;
import com.vinisnzy.order_service.dto.order.OrderRequestDTO;
import com.vinisnzy.order_service.dto.order.OrderResponseDTO;
import com.vinisnzy.order_service.dto.product.OrderProductDTO;
import com.vinisnzy.order_service.exceptions.ResourceNotFoundException;
import com.vinisnzy.order_service.mapper.OrderMapper;
import com.vinisnzy.order_service.repository.OrderProductRepository;
import com.vinisnzy.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderMapper mapper;

    public OrderResponseDTO createOrder(OrderRequestDTO dto) {
        Order order = mapper.toEntity(dto);
        addProductsToOrder(order, dto.items());
        order = orderRepository.save(order);
        return mapper.toDTO(order);
    }

    public List<OrderResponseDTO> getAllOrders() {
        List<Order> list = orderRepository.findAll();
        return list.stream()
                .map(mapper::toDTO)
                .toList();
    }

    public OrderResponseDTO getOrderById(Long id) {
        Order order = findById(id);
        return mapper.toDTO(order);
    }

    @Transactional
    public OrderResponseDTO updateOrder(Long id, OrderRequestDTO dto) {
        Order order = findById(id);
        mapper.update(dto, order);

        order.getItems().forEach(item -> {
            item.getProduct().getOrderProducts().remove(item);
        });
        order.getItems().clear();
        orderProductRepository.deleteByOrderId(id);

        addProductsToOrder(order, dto.items());

        order = orderRepository.save(order);
        return mapper.toDTO(order);
    }

    public void deleteOrder(Long id) {
        Order order = findById(id);
        orderRepository.delete(order);
    }

    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
    }

    public void save(Order order) {
        orderRepository.save(order);
    }

    private void addProductsToOrder(Order order, List<OrderProductDTO> products) {
        products.forEach(productDTO -> {
            var product = productService.findById(productDTO.productId());
            OrderProduct orderProduct = new OrderProduct(order, product, productDTO.quantity(), BigDecimal.valueOf(product.getPrice()));
            product.getOrderProducts().add(orderProduct);
            order.addItem(orderProduct);
        });
    }
}
