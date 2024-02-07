package com.example.orderitemproject_20240206.dto.request;

import lombok.Data;

@Data
public class CreateOrderItemsRequest {

    private String itemName;

    private String itemDescription;

    private Integer quantity;

    private Integer price;

}
