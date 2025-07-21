package com.vinisnzy.order_service.controller;

import com.vinisnzy.order_service.dto.product.ProductRequestDTO;
import com.vinisnzy.order_service.dto.product.ProductResponseDTO;
import com.vinisnzy.order_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO productRequest) {
         ProductResponseDTO createdProduct = service.createProduct(productRequest);
         return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
         List<ProductResponseDTO> products = service.getAllProducts();
         return ResponseEntity.ok().body(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id) {
         ProductResponseDTO product = service.getProductById(id);
         return ResponseEntity.ok().body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDTO productRequest) {
         ProductResponseDTO updatedProduct = service.updateProduct(id, productRequest);
         return ResponseEntity.ok().body(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
         service.deleteProduct(id);
         return ResponseEntity.noContent().build();
    }
}
