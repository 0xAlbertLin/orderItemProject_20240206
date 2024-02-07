package com.example.orderitemproject_20240206.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data

public class RetrieveOrder {

    private UUID id;

    private String itemName;

    private String itemDescription;

    public RetrieveOrder(UUID id, String itemName, String itemDescription) {
        this.id = id;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
    }
}
