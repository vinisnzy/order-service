package com.vinisnzy.order_service.mapper;

import com.vinisnzy.order_service.domain.Product;
import com.vinisnzy.order_service.dto.product.ProductRequestDTO;
import com.vinisnzy.order_service.dto.product.ProductResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderProducts", ignore = true)
    Product toEntity(ProductRequestDTO productRequestDTO);

    ProductResponseDTO toDTO(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "price", source = "price")
    void update(ProductRequestDTO productRequestDTO, @MappingTarget Product product);
}
