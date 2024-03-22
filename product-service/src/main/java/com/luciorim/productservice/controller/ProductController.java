package com.luciorim.productservice.controller;

import com.luciorim.productservice.dto.RequestProductDto;
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

        return ResponseEntity
                .ok(productService.getAllProducts());

    }

    @PostMapping
    public ResponseEntity<Void> createProduct(
            @RequestBody @Valid RequestProductDto requestProductDto){

        productService.createProduct(requestProductDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
