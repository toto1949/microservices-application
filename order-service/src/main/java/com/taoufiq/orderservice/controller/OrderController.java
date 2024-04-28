package com.taoufiq.orderservice.controller;

import com.taoufiq.orderservice.dto.OrderRequest;
import com.taoufiq.orderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventoryService", fallbackMethod = "fallbackMethod")
//    @TimeLimiter(name = "inventoryService")
    @Retry(name = "inventoryService")
    public String placeOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.placeOrder(orderRequest);
    }

    public CompletableFuture<String> fallbackMethod(OrderRequest orderRequest, RuntimeException e) {
        return CompletableFuture.supplyAsync(() -> "Oops! Something went wrong, please try again later");
    }
}