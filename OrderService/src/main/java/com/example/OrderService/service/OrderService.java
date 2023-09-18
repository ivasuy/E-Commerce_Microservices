package com.example.OrderService.service;

import com.example.OrderService.entity.Order;
import com.example.OrderService.model.OrderRequest;
import com.example.OrderService.model.OrderResponse;

import java.util.List;

public interface OrderService {
    String placeOrder(OrderRequest orderRequest);

    OrderResponse getOrderDetails(Long orderId);

    List<Order> getOrderByCustomerId(Long customerId);

    String updateOrderStatus(Long vendorId, String orderStatus);
}
