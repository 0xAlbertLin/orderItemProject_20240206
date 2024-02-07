package com.example.orderitemproject_20240206.repositories;

import com.example.orderitemproject_20240206.dto.response.RetrieveOrder;
import com.example.orderitemproject_20240206.entities.OrderItemsEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface OrderItemsRepository extends JpaRepository<OrderItemsEntity, UUID>, JpaSpecificationExecutor<OrderItemsEntity> {

    @Query("SELECT new com.example.orderitemproject_20240206.dto.response.RetrieveOrder(o.id, o.itemName, o.itemDescription) FROM OrderItemsEntity o")
    List<RetrieveOrder> findAllOrdersShowItemAndDescription();

    @Modifying
    @Transactional
    @Query("UPDATE OrderItemsEntity o SET o.quantity = ?2, o.price = ?3, o.updatedAt = ?4 WHERE o.id = ?1")
    void updateOrderQuantityAndPriceById(UUID id, Integer quantity, Integer price, LocalDateTime updatedAt);

    @Modifying
    @Transactional
    @Query("UPDATE OrderItemsEntity o SET o.itemName = ?2, o.updatedAt = ?3 WHERE o.id = ?1")
    void updateOrderName(UUID id, String itemName, LocalDateTime updatedAt);

}