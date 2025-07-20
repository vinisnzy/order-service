package com.vinisnzy.order_service.repository;

import com.vinisnzy.order_service.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
