package com.luciorim.productservice.controller;

import com.luciorim.productservice.dto.RequestCreateOrderDto;
import com.luciorim.productservice.dto.CreateProductDto;
import com.luciorim.productservice.dto.ResponseProductsEnabledDto;
import com.luciorim.productservice.dto.ResponseProductDto;
import com.luciorim.productservice.serivce.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
//import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ResponseProductDto>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody @Valid CreateProductDto createProductDto){
        productService.createProduct(createProductDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/check-stock")
    public ResponseEntity<ResponseProductsEnabledDto> isInStock(@RequestBody @Valid RequestCreateOrderDto requestCreateOrderDtos){
        return ResponseEntity.ok(productService.areInStock(requestCreateOrderDtos));
    }
}
