package com.example.ItemService.service;

import com.example.ItemService.entity.Item;
import com.example.ItemService.exception.ItemException;
import com.example.ItemService.model.ItemRequest;
import com.example.ItemService.model.ItemResponse;
import com.example.ItemService.repository.ItemRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImp implements ItemService{

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public String addItem(ItemRequest itemRequest) {

        Item item = Item.builder()
                .itemName(itemRequest.getName())
                .itemDescription(itemRequest.getDescription())
                .itemPrice(itemRequest.getPrice())
                .vendorId(itemRequest.getVendorId())
                .itemStockQuantity(itemRequest.getStockQuantity())
                .build();

        itemRepository.save(item);

        return "Item Created Successfully !";
    }

    @Override
    public ItemResponse getItem(Long itemId) {

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ItemException("Item with given Id does not exist","ITEM_NOT_FOUND"));
        ItemResponse itemResponse = new ItemResponse();
        BeanUtils.copyProperties(item,itemResponse);

        return itemResponse;
    }

    @Override
    public String updateItem(Long itemId, ItemRequest itemRequest) {

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ItemException("Item with given Id does not exist","ITEM_NOT_FOUND"));

        if(!item.getVendorId().equals(itemRequest.getVendorId())){
            throw new ItemException("Vendor does not have permission to update this item","UNAUTHORIZED_ACCESS");
        }

        if (itemRequest.getName() != null) {
            item.setItemName(itemRequest.getName());
        }
        if (itemRequest.getDescription() != null) {
            item.setItemDescription(itemRequest.getDescription());
        }
        if (itemRequest.getPrice() != null) {
            item.setItemPrice(itemRequest.getPrice());
        }
        if (itemRequest.getStockQuantity() != null) {
            item.setItemStockQuantity(itemRequest.getStockQuantity());
        }

        itemRepository.save(item);

        return "Item Updated Successfully !";
    }

    @Override
    public String deleteItem(Long itemId) {

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ItemException("Item with given Id does not exist","ITEM_NOT_FOUND"));
        itemRepository.delete(item);

        return "Item Deleted Successfully !";
    }

    @Override
    public List<Item> getItemsByVendor(Long vendorId) {

        List<Item> items = itemRepository.findByVendorId(vendorId);

        if(items.isEmpty()){
            throw new ItemException("Item with given Vendor Id does not exist", "ITEM_NOT_FOUND");
        }

        return items;
    }

    @Override
    public List<Item> getAllItems(String itemName) {

        List<Item> items = itemRepository.findByItemName(itemName);

        if(items.isEmpty()){
            throw new ItemException("Item with given name does not exist", "ITEM_NOT_FOUND");
        }

        return items;
    }


}
