package com.luciorim.productservice.serivce;

import com.luciorim.productservice.dto.*;
import com.luciorim.productservice.mapper.ProductMapper;
import com.luciorim.productservice.model.Product;
import com.luciorim.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final WebClient webClient;

    public List<ResponseProductDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void createProduct(CreateProductDto createProductDto) {
        var product = new Product();
        product.setProductName(createProductDto.getProductName());
        product.setDescription(createProductDto.getDescription());
        product.setProductPrice(createProductDto.getProductPrice());

        //call image-service to upload photo to S3 and get url
        if(createProductDto.getImage() != null){
            RequestUploadImageDto requestUploadImageDto = new RequestUploadImageDto();
            requestUploadImageDto.setImage(createProductDto.getImage());

            ResponseUploadImageDto responseUploadImageDto = webClient.post()
                    .uri("http://image-service/api/images")
                    .body(BodyInserters.fromValue(requestUploadImageDto))
                    .retrieve()
                    .bodyToMono(ResponseUploadImageDto.class)
                    .block();

            product.setImageUrl(responseUploadImageDto.getImageUrl());
        }

        log.info("Created product: {}", product);
        productRepository.save(product);
    }

    @Override
    public ResponseProductsEnabledDto areInStock(RequestCreateOrderDto requestCreateOrderDto) {
        var productsToCheck = requestCreateOrderDto.getProductsToCheck();

        Map<String, Integer> skuCodeWithNeededQuantity = productsToCheck.stream()
                .collect(Collectors.toMap(CheckProductDto::getScuCode, CheckProductDto::getNeededQuantity));

        var products = productRepository
                .findProductsBySduCodeIn(
                        productsToCheck
                                .stream()
                                .map(CheckProductDto::getScuCode)
                                .collect(Collectors.toList())
                );

        if(products.size() != productsToCheck.size()){
            throw new IllegalArgumentException("There is no product with particular scu-code");
        }

        boolean enabled = true;
        for(var product : products){
            if(product.getEnabledQuantity() < skuCodeWithNeededQuantity.get(product.getSduCode()) || product.getEnabledQuantity() < 0){
                enabled = false;
                break;
            }
        }
        return ResponseProductsEnabledDto
                .builder().areAllEnabled(enabled).build();

    }


}
