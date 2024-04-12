package com.luciorim.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(of = "skuCode")
public class CheckProductDto {

    @NotNull
    @JsonProperty("sku_code")
    private String skuCode;

    @NotNull
    @Positive
    @JsonProperty("needed_quantity")
    private Integer neededQuantity;

}
