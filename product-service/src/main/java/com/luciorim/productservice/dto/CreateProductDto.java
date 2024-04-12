package com.luciorim.productservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CreateProductDto {

    @NotNull
    @JsonProperty("product_name")
    private String productName;

    @NotNull
    @JsonProperty("description")
    private String description;

    @Positive
    @NotNull
    @JsonProperty("product_price")
    private Long productPrice;

    @JsonProperty("iamge")
    private MultipartFile image;

}
