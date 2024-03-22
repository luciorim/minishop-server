package com.luciorim.orderservice.controller;

import com.luciorim.orderservice.dto.RequestCreateOrderDto;
import com.luciorim.orderservice.dto.ResponseOrderDto;
import com.luciorim.orderservice.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping()
    public ResponseEntity<Void> placeOrder(
            @RequestBody @Valid RequestCreateOrderDto requestCreateOrderDto){

        orderService.createOrder(requestCreateOrderDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @GetMapping()
    public ResponseEntity<List<ResponseOrderDto>> getAllOrders(){

        return ResponseEntity
                .ok(orderService.getAllOrders());

    }
}
