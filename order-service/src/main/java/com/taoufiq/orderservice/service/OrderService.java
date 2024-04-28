package com.taoufiq.orderservice.service;

import com.taoufiq.orderservice.dto.InventoryResponse;
import com.taoufiq.orderservice.dto.OrderLineItemsDto;
import com.taoufiq.orderservice.dto.OrderRequest;
import com.taoufiq.orderservice.event.OrderPlacedEvent;
import com.taoufiq.orderservice.model.Order;
import com.taoufiq.orderservice.model.OrderlineItems;
import com.taoufiq.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final WebClient webClientBuilder;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public String placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderlineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList().stream().map(this::mapToOrderLineItems).toList();
        order.setOrderLineItemsLis(orderLineItems);
        List<String> skuCodes = order.getOrderLineItemsLis().stream()
                .map(OrderlineItems::getSkuCode)
                .toList();
        InventoryResponse[] inventoryResponsesArray = webClientBuilder.get()
                .uri("http://localhost:8086/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();
        boolean allProductInStock = Arrays.stream(inventoryResponsesArray).allMatch(InventoryResponse::isInstock);
        if (allProductInStock) {
            orderRepository.save(order);
            kafkaTemplate.send("notificationTopic", new OrderPlacedEvent(order.getOrderNumber()));
            return "Order Placed Successfully";
        } else {
            throw new IllegalArgumentException("product is not in stock, please try again later");
        }
    }

    private OrderlineItems mapToOrderLineItems(OrderLineItemsDto orderLineItemsDto) {
        OrderlineItems orderLineItems = new OrderlineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
