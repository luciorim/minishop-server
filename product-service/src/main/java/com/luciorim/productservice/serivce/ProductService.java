package com.luciorim.productservice.serivce;

import com.luciorim.productservice.dto.RequestProductDto;
import com.luciorim.productservice.dto.ResponseProductDto;
import com.luciorim.productservice.mapper.ProductMapper;
import com.luciorim.productservice.model.Product;
import com.luciorim.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    public List<ResponseProductDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    public void createProduct(RequestProductDto requestProductDto) {

        var product = Product.builder()
                .productPrice(requestProductDto.getProductPrice())
                .productName(requestProductDto.getProductName())
                .description(requestProductDto.getDescription())
                .build();

        log.info("Created product: {}", product);

        productRepository.save(product);

    }
}
