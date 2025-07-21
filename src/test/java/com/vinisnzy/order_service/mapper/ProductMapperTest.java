package com.vinisnzy.order_service.mapper;

import com.vinisnzy.order_service.domain.Product;
import com.vinisnzy.order_service.dto.product.ProductRequestDTO;
import com.vinisnzy.order_service.dto.product.ProductResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class ProductMapperTest {
    
    private Product product;
    private ProductRequestDTO productRequestDTO;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setPrice(50.00);

        productRequestDTO = new ProductRequestDTO(
                product.getName(),
                product.getPrice()
        );
    }

    @Test
    void shouldConvertProductToProductResponseDTO_WhenProductIsValid() {
        ProductResponseDTO result = ProductMapper.INSTANCE.toDTO(product);

        assertNotNull(result);
        assertEquals(product.getId(), result.id());
        assertEquals(product.getName(), result.name());
        assertEquals(product.getPrice(), result.price());
    }

    @Test
    void shouldConvertProductRequestDTOToProduct_WhenDTOIsValid() {
        Product result = ProductMapper.INSTANCE.toEntity(productRequestDTO);

        assertNotNull(result);
        assertEquals(productRequestDTO.name(), result.getName());
        assertEquals(productRequestDTO.price(), result.getPrice());
    }
}