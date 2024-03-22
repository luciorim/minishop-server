package com.luciorim.orderservice.service;

import com.luciorim.orderservice.dto.RequestCreateOrderDto;
import com.luciorim.orderservice.dto.ResponseOrderDto;
import com.luciorim.orderservice.mapper.OrderLineItemMapper;
import com.luciorim.orderservice.mapper.OrderMapper;
import com.luciorim.orderservice.model.Order;
import com.luciorim.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderService {

    private final OrderLineItemMapper orderLineItemMapper;
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;

    public void createOrder(RequestCreateOrderDto requestCreateOrderDto) {

        log.info("List of items in requestOrderDto: {}", requestCreateOrderDto.getOrderItems());

        var order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .orderItems(
                        requestCreateOrderDto
                                .getOrderItems()
                                .stream()
                                .map(orderLineItemMapper::toEntity)
                                .collect(Collectors.toList())
                ).build();

        log.info("Created new Order: {}", order);

        orderRepository.save(order);

    }

    public List<ResponseOrderDto> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());

    }
}
