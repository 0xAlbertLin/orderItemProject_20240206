package com.example.orderitemproject_20240206.service;

import com.example.orderitemproject_20240206.dto.response.CustomResponse;
import com.example.orderitemproject_20240206.dto.response.RetrieveOrder;
import com.example.orderitemproject_20240206.entities.OrderItemsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.orderitemproject_20240206.repositories.OrderItemsRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class OrderItemsService {

    private final OrderItemsRepository orderItemsRepository;

    @Autowired
    public OrderItemsService(OrderItemsRepository orderItemsRepository) {
        this.orderItemsRepository = orderItemsRepository;
    }

    //建立訂單
    public CustomResponse createOrder(
            String itemName,
            String itemDescription,
            Integer quantity,
            Integer price) {
        OrderItemsEntity orderItemsEntity = new OrderItemsEntity();

        orderItemsEntity.setId(UUID.randomUUID());
        orderItemsEntity.setItemName(itemName);
        orderItemsEntity.setItemDescription(itemDescription);
        orderItemsEntity.setQuantity(quantity);
        orderItemsEntity.setPrice(price);
        orderItemsEntity.setStatus("Pending");
        orderItemsEntity.setCreatedAt(LocalDateTime.now());
        orderItemsRepository.save(orderItemsEntity);

        return new CustomResponse("The order has been placed");
    }

    // 查詢所有訂單(只顯示部分資訊)
    public List<RetrieveOrder> findAllOrdersShowItemAndDescription() {
        return orderItemsRepository.findAllOrdersShowItemAndDescription();
    }

    // 查詢所有訂單(顯示所有資訊)
    public List<OrderItemsEntity> findAllOrdersDetail() {
        return orderItemsRepository.findAll();
    }







//    public Optional<OrderItemsEntity> findOrderById(UUID id) {
//        return orderItemsRepository.findById(id);
//    }



    public CustomResponse updateOrderQuantityAndPrice(UUID id, Integer quantity, Integer price) {
        orderItemsRepository.updateOrderQuantityAndPriceById(id, quantity, price, LocalDateTime.now());
        return new CustomResponse("The order Quantity And Price has been update");
    }

    public CustomResponse updateOrderName(UUID id, String itemName) {
        orderItemsRepository.updateOrderName(id, itemName, LocalDateTime.now());
        return new CustomResponse("The order Name has been update");
    }

    public CustomResponse deleteOrder(UUID id) {
        orderItemsRepository.deleteById(id);
        return new CustomResponse("The order Name has been delete");
    }

}
