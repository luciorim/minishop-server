package com.luciorim.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class ResponseOrderDto {

    @JsonProperty("order_number")
    private String orderNumber;

    @JsonProperty("total_price")
    private Long totalPrice;

    @JsonProperty("order_items")
    private Map<String, Integer> orderItems;
}
