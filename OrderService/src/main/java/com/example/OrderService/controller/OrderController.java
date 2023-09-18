package com.example.OrderService.controller;

import com.example.OrderService.entity.Order;
import com.example.OrderService.model.OrderRequest;
import com.example.OrderService.model.OrderResponse;
import com.example.OrderService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PreAuthorize("hasAnyAuthority('Admin')")
    @PostMapping
    public ResponseEntity<String> placeOrder(@RequestBody OrderRequest orderRequest){

        String orderStatus = orderService.placeOrder(orderRequest);
        return new ResponseEntity<>(orderStatus, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderDetails(@PathVariable("id") Long orderId){

        OrderResponse orderResponse = orderService.getOrderDetails(orderId);
        return new ResponseEntity<>(orderResponse, HttpStatus.FOUND);
    }

    @PreAuthorize("hasAnyAuthority('Admin')")
    @GetMapping("/customers/{id}")
    public ResponseEntity<
    List<OrderResponse>> getOrderByCustomerId(@PathVariable ("id") Long customerId){

        List<Order> orders = orderService.getOrderByCustomerId(customerId);

        List<OrderResponse> orderResponses = orders.stream()
                .map(order -> {
                    OrderResponse orderResponse = new OrderResponse();
                    orderResponse.setOrderId(order.getOrderId());
                    orderResponse.setCustomerId(order.getCustomerId());
                    orderResponse.setVendorId(order.getVendorId());
                    orderResponse.setOrderDate(order.getOrderDate());
                    orderResponse.setOrderStatus(order.getOrderStatus());
                    orderResponse.setOrderTotalAmount(order.getOrderTotalAmount());
                    return orderResponse;
                })
                .collect(Collectors.toList());

        return new ResponseEntity<>(orderResponses, HttpStatus.FOUND);

    }

    @PreAuthorize("hasAnyAuthority('Admin')")
    @PutMapping ("/vendors/{id}")
    public ResponseEntity<String> updateOrderStatus(
            @PathVariable("id") Long vendorId,
            @RequestParam String orderStatus){
        String updateStatus = orderService.updateOrderStatus(vendorId, orderStatus);
        return new ResponseEntity<>(updateStatus, HttpStatus.OK);
    }

}
