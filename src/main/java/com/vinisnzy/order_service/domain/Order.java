package com.vinisnzy.order_service.domain;

import com.vinisnzy.order_service.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String clientName;
    private LocalDateTime createdAt;
    private BigDecimal subtotal;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProduct> products = new ArrayList<>();

    public Order(String clientName) {
        this.clientName = clientName;
        this.createdAt = LocalDateTime.now();
        this.subtotal = new BigDecimal(0);
        this.status = OrderStatus.PENDING;
    }
}
