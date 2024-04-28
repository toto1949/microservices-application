package com.taoufiq.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private List<OrderLineItemsDto> orderLineItemsDtoList;
    //give me example json from postman
    // {"orderLineItemsDtoList":[{"skuCode":"Iphone13","quantity":1,"price":1000},{"skuCode":"Iphone14","quantity":1,"price":1000}]}
}
