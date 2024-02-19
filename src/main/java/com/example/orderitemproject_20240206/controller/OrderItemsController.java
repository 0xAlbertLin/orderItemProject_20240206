package com.example.orderitemproject_20240206.controller;

import com.example.orderitemproject_20240206.dto.request.CreateOrderItemsRequest;
import com.example.orderitemproject_20240206.dto.response.BaseResponse;
import com.example.orderitemproject_20240206.dto.response.CustomResponse;
import com.example.orderitemproject_20240206.dto.response.RetrieveOrder;
import com.example.orderitemproject_20240206.entities.OrderItemsEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.orderitemproject_20240206.services.OrderItemsService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/order")
public class OrderItemsController {
    private final OrderItemsService orderService;

    public OrderItemsController(OrderItemsService orderService) {
        this.orderService = orderService;
    }


    /**
     * Create a new order item 建立新的訂單
     * @param createOrderItemsRequest
     * @return
     */
    @PostMapping(value = "/create")
    public ResponseEntity<BaseResponse<Object>> createOrder(
            @RequestBody CreateOrderItemsRequest createOrderItemsRequest
            ) {
        CustomResponse result = orderService.createOrder(
                createOrderItemsRequest.getItemName(),
                createOrderItemsRequest.getItemDescription(),
                createOrderItemsRequest.getQuantity(),
                createOrderItemsRequest.getPrice()
                );
        return ResponseEntity.ok().body(new BaseResponse<>("success", HttpStatus.OK.value(), result));
    }

    @GetMapping(value = "/getallorders")
    public List<RetrieveOrder> getAllOrders() {
        return orderService.findAllOrdersShowItemAndDescription();
    }

    @GetMapping(value = "/getallordersdetail")
    public List<OrderItemsEntity> getAllOrdersDetail() {
        return orderService.findAllOrdersDetail();
    }

    @PutMapping(value = "/update_quantity_and_price/{id}")
    public ResponseEntity<BaseResponse<Object>> updateQuantityAndPrice(
            @PathVariable UUID id,
            @RequestParam("quantity") Integer quantity,
            @RequestParam("price") Integer price
    ) {

        CustomResponse result = orderService.updateOrderQuantityAndPrice(id, quantity, price);

        return ResponseEntity.ok().body(new BaseResponse<>("success", HttpStatus.OK.value(), result));
    }

    @PutMapping(value = "/update_order_name/{id}")
    public ResponseEntity<BaseResponse<Object>> updateOrderName(
            @PathVariable UUID id,
            @RequestParam("item_name") String itemName
    ) {

        CustomResponse result = orderService.updateOrderName(id, itemName);

        return ResponseEntity.ok().body(new BaseResponse<>("success", HttpStatus.OK.value(), result));

    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<BaseResponse<Object>> deleteOrder(
            @PathVariable UUID id
    ) {

        CustomResponse result = orderService.deleteOrder(id);
        return ResponseEntity.ok().body(new BaseResponse<>("success", HttpStatus.OK.value(), result));

    }


}
