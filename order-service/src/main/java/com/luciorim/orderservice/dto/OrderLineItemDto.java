package com.luciorim.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderLineItemDto {

    @NotNull
    @JsonProperty("sku_code")
    private String skuCode;

    @NotNull
    @Positive
    @JsonProperty("price")
    private Long price;

    @NotNull
    @Positive
    @JsonProperty("quantity")
    private Integer quantity;

}
