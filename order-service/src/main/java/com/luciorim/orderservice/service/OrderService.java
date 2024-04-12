package com.luciorim.orderservice.service;

import com.luciorim.orderservice.dto.*;
import com.luciorim.orderservice.event.OrderPlacedEvent;
import com.luciorim.orderservice.mapper.OrderMapper;
import com.luciorim.orderservice.model.Order;
import com.luciorim.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final WebClient webClient;
    private final KafkaTemplate kafkaTemplate;

    public void createOrder(RequestCreateOrderDto requestCreateOrderDto) {

        log.info("List of items in requestOrderDto: {}", requestCreateOrderDto.getProducts());

        //call product service and place order if prod is in stock
        var productsEnabled = webClient.post()
                .uri("http://product-service/api/products/check-stock")
                .body(BodyInserters.fromValue(requestCreateOrderDto))
                .retrieve()
                .bodyToMono(ResponseProductsEnabledDto.class)
                .block();

        if(productsEnabled != null && productsEnabled.getAreAllEnabled()) {

            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
//            order.setOrderItems();

            kafkaTemplate.send("notificationTopic", new OrderPlacedEvent(order.getOrderNumber()));
            orderRepository.save(order);
            log.info("Created new Order: {}", order);
        }

    }

    public List<ResponseOrderDto> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());

    }
}
