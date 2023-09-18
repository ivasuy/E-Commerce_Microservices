package com.example.ItemService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemRequest {
    private Long vendorId;
    private String name;
    private String description;
    private Long price;
    private Long stockQuantity;
}
