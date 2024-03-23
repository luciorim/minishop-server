package com.luciorim.orderservice.controller;

import com.luciorim.orderservice.dto.FallbackDto;
import com.luciorim.orderservice.dto.RequestCreateOrderDto;
import com.luciorim.orderservice.dto.ResponseOrderDto;
import com.luciorim.orderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping()
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallback")
    @TimeLimiter(name = "inventory")
    @Retry(name = "inventory")
    public CompletableFuture<ResponseEntity<Void>> placeOrder(
            @RequestBody @Valid RequestCreateOrderDto requestCreateOrderDto){

        return CompletableFuture.supplyAsync(() -> {
            orderService.createOrder(requestCreateOrderDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        });

    }

    @GetMapping()
    public ResponseEntity<List<ResponseOrderDto>> getAllOrders(){

        return ResponseEntity
                .ok(orderService.getAllOrders());

    }

    public ResponseEntity<FallbackDto> fallback(
            RequestCreateOrderDto requestCreateOrderDto, RuntimeException runtimeException){

        return ResponseEntity.internalServerError().body(
                FallbackDto.builder()
                        .message("Something went wrong, try again later")
                        .build()
        );

    }
}
