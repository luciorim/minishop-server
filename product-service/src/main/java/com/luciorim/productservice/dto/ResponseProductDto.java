package com.luciorim.productservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseProductDto {

    @JsonProperty("id")
    private String id;

    @JsonProperty("product_name")
    private String productName;

    @JsonProperty("description")
    private String description;

    @JsonProperty("product_price")
    private Long productPrice;

}
