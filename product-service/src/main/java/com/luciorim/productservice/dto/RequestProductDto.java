package com.luciorim.productservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class RequestProductDto {

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

}
