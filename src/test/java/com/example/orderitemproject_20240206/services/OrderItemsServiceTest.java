package com.example.orderitemproject_20240206.services;

import com.example.orderitemproject_20240206.dto.response.CustomResponse;
import com.example.orderitemproject_20240206.entities.OrderItemsEntity;
import com.example.orderitemproject_20240206.repositories.OrderItemsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
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
        //Sample
        String itemName = "Test Item";
        String itemDescription = "Test Description";
        Integer quantity = 10;
        Integer price = 100;

        when(orderItemsRepository.save(any(OrderItemsEntity.class))).thenAnswer(i -> i.getArguments()[0]);

        CustomResponse response = orderItemsService.createOrder(itemName, itemDescription, quantity, price);

        assertNotNull(response);
        assertEquals("The order has been placed", response.getResult());
        verify(orderItemsRepository, times(1)).save(any(OrderItemsEntity.class));

    }
}
