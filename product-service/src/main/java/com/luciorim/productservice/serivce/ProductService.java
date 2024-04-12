package com.luciorim.productservice.serivce;

import com.luciorim.productservice.dto.RequestCreateOrderDto;
import com.luciorim.productservice.dto.CreateProductDto;
import com.luciorim.productservice.dto.ResponseProductsEnabledDto;
import com.luciorim.productservice.dto.ResponseProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<ResponseProductDto> getAllProducts();

    void createProduct(CreateProductDto createProductDto);

    ResponseProductsEnabledDto areInStock(RequestCreateOrderDto requestCreateOrderDtos);
}
