package com.luciorim.orderservice.service;

import com.luciorim.orderservice.dto.OrderLineItemDto;
import com.luciorim.orderservice.dto.RequestCreateOrderDto;
import com.luciorim.orderservice.dto.ResponseInventoryDto;
import com.luciorim.orderservice.dto.ResponseOrderDto;
import com.luciorim.orderservice.mapper.OrderLineItemMapper;
import com.luciorim.orderservice.mapper.OrderMapper;
import com.luciorim.orderservice.model.Order;
import com.luciorim.orderservice.model.OrderLineItem;
import com.luciorim.orderservice.repository.OrderLineItemRepository;
import com.luciorim.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
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
    private final OrderLineItemRepository orderLineItemRepository;
    private final WebClient.Builder webClientBuilder;

    public void createOrder(RequestCreateOrderDto requestCreateOrderDto) {

        log.info("List of items in requestOrderDto: {}", requestCreateOrderDto.getOrderItems());

        List<OrderLineItem> orderLineItems = requestCreateOrderDto
                .getOrderItems()
                .stream()
                .map(orderLineItemMapper::toEntity)
                .toList();

        Map<String, Integer> skuCodeWithNeededQuantity = orderLineItems.stream()
                .collect(Collectors.toMap(OrderLineItem::getSkuCode, OrderLineItem::getQuantity));

        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .orderItems(orderLineItems)
                .build();

        List<String> scuCodes = requestCreateOrderDto
                .getOrderItems()
                .stream()
                .map(OrderLineItemDto::getSkuCode)
                .toList();

        //call inventory service and place order if prod is in stock

       ResponseInventoryDto[] responseInventoryDtos = webClientBuilder.build().get()
                            .uri("http://inventory-service/api/inventories",
                                    uriBuilder -> uriBuilder.queryParam("skuCodes", scuCodes).build())
                            .retrieve()
                            .bodyToMono(ResponseInventoryDto[].class)
                            .block();

       for(ResponseInventoryDto returnedInventory: responseInventoryDtos){

           var skuCode = returnedInventory.getSkuCode();
           var enabledQuantity = returnedInventory.getEnabledQuantity();

           if(skuCodeWithNeededQuantity.get(skuCode) > enabledQuantity){

               throw new IllegalArgumentException("There is no enough products, please try again later");

           }

       }

       var productsInStock = Arrays.stream(responseInventoryDtos)
                       .allMatch(ResponseInventoryDto::isInStock);

       if(productsInStock) {
           orderRepository.save(order);
           log.info("Created new Order: {}", order);
       }else{
           throw new IllegalArgumentException("Products not in stock, please try again later");
       }



    }

    public List<ResponseOrderDto> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());

    }
}
