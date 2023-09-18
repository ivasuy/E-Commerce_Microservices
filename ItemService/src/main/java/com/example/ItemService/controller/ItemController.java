package com.example.ItemService.controller;

import com.example.ItemService.entity.Item;
import com.example.ItemService.model.ItemRequest;
import com.example.ItemService.model.ItemResponse;
import com.example.ItemService.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PreAuthorize("hasAnyAuthority('Admin')")
    @PostMapping
    public ResponseEntity<String> addItem(@RequestBody ItemRequest itemRequest){

        String itemStatus = itemService.addItem(itemRequest);
        return new ResponseEntity<>(itemStatus, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ItemResponse>> getAllItems(@RequestParam String itemName){

        List<Item> items = itemService.getAllItems(itemName);

        List<ItemResponse> itemResponses = items.stream()
                .map(item -> {
                    ItemResponse itemResponse = new ItemResponse();
                    itemResponse.setItemId(item.getItemId());
                    itemResponse.setVendorId(item.getVendorId());
                    itemResponse.setItemName(item.getItemName());
                    itemResponse.setItemDescription(item.getItemDescription());
                    itemResponse.setItemPrice(item.getItemPrice());
                    itemResponse.setItemStockQuantity(item.getItemStockQuantity());
                    return itemResponse;
                })
                .collect(Collectors.toList());

        return new ResponseEntity<>(itemResponses,HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemResponse> getItem(@PathVariable("id") Long itemId){

        ItemResponse itemResponse = itemService.getItem(itemId);
        return new ResponseEntity<>(itemResponse,HttpStatus.FOUND);
    }

    @PreAuthorize("hasAnyAuthority('Admin')")
    @GetMapping("/vendors/{id}")
    public ResponseEntity<List<ItemResponse>> getItemsByVendor(@PathVariable("id") Long vendorId){

        List<Item> items = itemService.getItemsByVendor(vendorId);

        List<ItemResponse> itemResponses = items.stream()
                .map(item -> {
                    ItemResponse itemResponse = new ItemResponse();
                    itemResponse.setItemId(item.getItemId());
                    itemResponse.setVendorId(item.getVendorId());
                    itemResponse.setItemName(item.getItemName());
                    itemResponse.setItemDescription(item.getItemDescription());
                    itemResponse.setItemPrice(item.getItemPrice());
                    itemResponse.setItemStockQuantity(item.getItemStockQuantity());
                    return itemResponse;
                })
                .collect(Collectors.toList());

        return new ResponseEntity<>(itemResponses,HttpStatus.FOUND);
    }

    @PreAuthorize("hasAnyAuthority('Admin')")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateItem(
            @PathVariable("id") Long itemId,
            @RequestBody ItemRequest itemRequest
            ){
        String itemStatus = itemService.updateItem(itemId, itemRequest);
        return new ResponseEntity<>(itemStatus, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('Admin')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable("id") Long itemId){

        String itemStatus = itemService.deleteItem(itemId);

        return new ResponseEntity<>(itemStatus, HttpStatus.OK);
    }

}
