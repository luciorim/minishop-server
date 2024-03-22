package com.luciorim.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.luciorim.orderservice.model.OrderLineItem;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResponseOrderDto {

    @JsonProperty("order_number")
    private String orderNumber;

    @JsonProperty("order_items")
    private List<OrderLineItemDto> orderItems;
}
