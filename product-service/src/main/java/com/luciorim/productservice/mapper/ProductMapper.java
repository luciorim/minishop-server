package com.luciorim.productservice.mapper;

import com.luciorim.productservice.dto.ResponseProductDto;
import com.luciorim.productservice.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ResponseProductDto toDto(Product product){
        return ResponseProductDto.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .description(product.getDescription())
                .productPrice(product.getProductPrice())
                .build();
    }
}
