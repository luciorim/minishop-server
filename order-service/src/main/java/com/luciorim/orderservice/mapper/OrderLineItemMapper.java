package com.luciorim.orderservice.mapper;

import com.luciorim.orderservice.dto.OrderLineItemDto;
import com.luciorim.orderservice.model.OrderLineItem;
import org.springframework.stereotype.Component;

@Component
public class OrderLineItemMapper implements BaseMapper<OrderLineItem, OrderLineItemDto>{
    @Override
    public OrderLineItemDto toDto(OrderLineItem orderLineItem) {
        return OrderLineItemDto.builder()
                .skuCode(orderLineItem.getSkuCode())
                .price(orderLineItem.getPrice())
                .quantity(orderLineItem.getQuantity())
                .build();
    }

    public OrderLineItem toEntity(OrderLineItemDto orderLineItemDto){
        return OrderLineItem.builder()
                .price(orderLineItemDto.getPrice())
                .skuCode(orderLineItemDto.getSkuCode())
                .quantity(orderLineItemDto.getQuantity())
                .build();
    }
}
