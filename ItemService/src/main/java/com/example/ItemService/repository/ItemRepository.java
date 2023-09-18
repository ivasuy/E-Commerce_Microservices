package com.example.ItemService.repository;

import com.example.ItemService.entity.Item;
import com.example.ItemService.model.ItemResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByVendorId(Long vendorId);

    List<Item> findByItemName(String itemName);
}
