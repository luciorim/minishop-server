package com.luciorim.orderservice.mapper;

import com.luciorim.orderservice.dto.ResponseOrderDto;
import com.luciorim.orderservice.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderMapper implements BaseMapper<Order, ResponseOrderDto>{

    private final OrderLineItemMapper orderLineItemMapper;

    public ResponseOrderDto toDto(Order order){
        return ResponseOrderDto.builder()
                .orderNumber(order.getOrderNumber())
                .orderItems(
                        order.
                                getOrderItems()
                                .stream()
                                .map(orderLineItemMapper::toDto)
                                .collect(Collectors.toList())

                ).build();
    }
}
