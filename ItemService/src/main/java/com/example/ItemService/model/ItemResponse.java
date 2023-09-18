package com.example.ItemService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemResponse {
    private Long itemId;
    private Long vendorId;
    private String itemName;
    private String itemDescription;
    private Long itemPrice;
    private Long itemStockQuantity;
}
