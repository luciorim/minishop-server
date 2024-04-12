package com.luciorim.orderservice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class ResponseProductsEnabledDto {

    private Map<String, Integer> productsWithQuantity;

    private Boolean areAllEnabled;
}

