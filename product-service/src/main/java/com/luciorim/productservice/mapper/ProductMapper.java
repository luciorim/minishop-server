package com.luciorim.productservice.mapper;

import com.luciorim.productservice.dto.ResponseProductDto;
import com.luciorim.productservice.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ResponseProductDto toDto(Product product){
        ResponseProductDto responseProductDto = new ResponseProductDto();
        responseProductDto.setProductName(product.getProductName());
        responseProductDto.setDescription(product.getDescription());
        responseProductDto.setProductPrice(product.getProductPrice());
        responseProductDto.setImageUrl(product.getImageUrl());
        return responseProductDto;
    }
}
