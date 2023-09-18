package com.example.OrderService.service;

import com.example.OrderService.entity.Order;
import com.example.OrderService.exception.OrderException;
import com.example.OrderService.model.OrderRequest;
import com.example.OrderService.model.OrderResponse;
import com.example.OrderService.repository.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class OrderServiceImp implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public String placeOrder(OrderRequest orderRequest) {

        Order order = Order.builder()
                .customerId(orderRequest.getCustomerId())
                .vendorId(orderRequest.getVendorId())
                .orderDate(Instant.now())
                .orderTotalAmount(orderRequest.getOrderTotalAmount())
                .orderStatus("ORDER_PLACED")
                .build();

        orderRepository.save(order);

        return "Order Placed Successfully !";
    }

    @Override
    public OrderResponse getOrderDetails(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderException("Order with given Id does not exist","ORDER_NOT_FOUND"));

        OrderResponse orderResponse = new OrderResponse();

        BeanUtils.copyProperties(order,orderResponse);

        return orderResponse;
    }

    @Override
    public List<Order> getOrderByCustomerId(Long customerId) {

        List<Order> orders = orderRepository.findByCustomerId(customerId);

        if(orders.isEmpty()){
            throw new OrderException("Order with given Customer Id does not exist","ORDER_NOT_FOUND");
        }

        return orders;
    }

    @Override
    public String updateOrderStatus(Long vendorId, String orderStatus) {

        Order order = orderRepository.findByVendorId(vendorId);

        if(order == null){
            throw new OrderException("Order with given Vendor Id does not exist","ORDER_NOT_FOUND");
        }

        if(!order.getVendorId().equals(vendorId)){
            throw new OrderException("Vendor does not have permission to update this order","UNAUTHORIZED_ACCESS");
        }

        if(orderStatus != null){
            order.setOrderStatus(orderStatus);
        }

        orderRepository.save(order);

        return "Order Status Updated Successfully";
    }
}
