package com.example.ItemService.service;

import com.example.ItemService.entity.Item;
import com.example.ItemService.model.ItemRequest;
import com.example.ItemService.model.ItemResponse;

import java.util.List;

public interface ItemService {
    String addItem(ItemRequest itemRequest);

    ItemResponse getItem(Long itemId);

    String updateItem(Long itemId, ItemRequest itemRequest);

    String deleteItem(Long itemId);

    List<Item> getItemsByVendor(Long vendorId);

    List<Item> getAllItems(String itemName);
}
