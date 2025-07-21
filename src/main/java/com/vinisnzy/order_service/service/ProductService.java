package com.vinisnzy.order_service.service;

import com.vinisnzy.order_service.domain.Product;
import com.vinisnzy.order_service.dto.product.ProductRequestDTO;
import com.vinisnzy.order_service.dto.product.ProductResponseDTO;
import com.vinisnzy.order_service.exceptions.ResourceNotFoundException;
import com.vinisnzy.order_service.mapper.ProductMapper;
import com.vinisnzy.order_service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper mapper;

    public ProductResponseDTO createProduct(ProductRequestDTO dto) {
        Product product = mapper.toEntity(dto);
        productRepository.save(product);
        return mapper.toDTO(product);
    }

    public List<ProductResponseDTO> getAllProducts() {
        List<Product> list = productRepository.findAll();
        return list.stream()
                .map(mapper::toDTO)
                .toList();
    }

    public ProductResponseDTO getProductById(Long id) {
        Product product = findById(id);
        return mapper.toDTO(product);
    }

    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO dto) {
        Product product = findById(id);
        mapper.update(dto, product);
        product = productRepository.save(product);
        return mapper.toDTO(product);
    }

    public void deleteProduct(Long id) {
        Product product = findById(id);
        productRepository.delete(product);
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }
}
