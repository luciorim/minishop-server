package com.luciorim.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResponseInventoryDto {

    @JsonProperty("sku_code")
    private String skuCode;

    @JsonProperty("enabled_quantity")
    private Integer enabledQuantity;

    @JsonProperty("is_in_stock")
    private boolean isInStock;

}
