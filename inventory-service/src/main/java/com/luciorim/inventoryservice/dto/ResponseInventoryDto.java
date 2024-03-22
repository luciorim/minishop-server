package com.luciorim.inventoryservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResponseInventoryDto {

    @JsonProperty("sku_code")
    private String skuCode;

    @JsonProperty("is_in_stock")
    private Boolean isInStock;

}
