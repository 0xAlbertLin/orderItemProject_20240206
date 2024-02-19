package com.example.orderitemproject_20240206.services;

import com.example.orderitemproject_20240206.dto.response.CustomResponse;
import com.example.orderitemproject_20240206.dto.response.RetrieveOrder;
import com.example.orderitemproject_20240206.entities.OrderItemsEntity;
import com.example.orderitemproject_20240206.repositories.OrderItemsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class OrderItemsServiceTest {
    @Mock
    private OrderItemsRepository orderItemsRepository;

    @InjectMocks
    private OrderItemsService orderItemsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateOrder() {
        // Arrange
        String itemName = "Test Item";
        String itemDescription = "Test Description";
        Integer quantity = 10;
        Integer price = 100;

        when(orderItemsRepository.save(any(OrderItemsEntity.class))).thenAnswer(i -> i.getArguments()[0]);
        // Act
        CustomResponse response = orderItemsService.createOrder(itemName, itemDescription, quantity, price);

        assertNotNull(response);
        assertEquals("The order has been placed", response.getResult());
        verify(orderItemsRepository, times(1)).save(any(OrderItemsEntity.class));

    }

    @Test
    public void findAllOrdersShowItemAndDescriptionTest() {
        // Arrange
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();

        when(orderItemsRepository.findAllOrdersShowItemAndDescription()).thenReturn(Arrays.asList(
                new RetrieveOrder(id1, "Item1", "Description1"),
                new RetrieveOrder(id2, "Item2", "Description2")
        ));

        // Act
        List<RetrieveOrder> result = orderItemsService.findAllOrdersShowItemAndDescription();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(id1, result.get(0).getId());
        assertEquals("Item1", result.get(0).getItemName());
        assertEquals("Description1", result.get(0).getItemDescription());
        assertEquals(id2, result.get(1).getId());
        assertEquals("Item2", result.get(1).getItemName());
        assertEquals("Description2", result.get(1).getItemDescription());
    }

    @Test
    public void findAllOrdersDetailTest() {
        // Arrange
        when(orderItemsRepository.findAll()).thenReturn(Arrays.asList(
                new OrderItemsEntity(),
                new OrderItemsEntity()
        ));

        // Act
        List<OrderItemsEntity> result = orderItemsService.findAllOrdersDetail();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(orderItemsRepository, times(1)).findAll();
    }

    @Test
    public void updateOrderQuantityAndPriceTest() {
        // Arrange
        UUID id = UUID.randomUUID();
        Integer quantity = 20;
        Integer price = 200;
        doNothing().when(orderItemsRepository).updateOrderQuantityAndPriceById(id, quantity, price, LocalDateTime.now());

        // Act
        CustomResponse response = orderItemsService.updateOrderQuantityAndPrice(id, quantity, price);

        // Assert
        assertNotNull(response);
        assertEquals("The order Quantity And Price has been update", response.getResult());
        verify(orderItemsRepository, times(1)).updateOrderQuantityAndPriceById(eq(id), eq(quantity), eq(price), any(LocalDateTime.class));
    }

    @Test
    void updateOrderNameTest() {
        // Arrange
        UUID id = UUID.randomUUID();
        String itemName = "Updated Item Name";
        doNothing().when(orderItemsRepository).updateOrderName(id, itemName, LocalDateTime.now());

        // Act
        CustomResponse response = orderItemsService.updateOrderName(id, itemName);

        // Assert
        assertNotNull(response);
        assertEquals("The order Name has been update", response.getResult());
        verify(orderItemsRepository, times(1)).updateOrderName(eq(id), eq(itemName), any(LocalDateTime.class));
    }

    @Test
    void deleteOrderTest() {
        // Arrange
        UUID id = UUID.randomUUID();
        doNothing().when(orderItemsRepository).deleteById(id);

        // Act
        CustomResponse response = orderItemsService.deleteOrder(id);

        // Assert
        assertNotNull(response);
        assertEquals("The order Name has been delete", response.getResult());
        verify(orderItemsRepository, times(1)).deleteById(id);
    }
}
