package com.example.OrderService.model;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private Long orderId;
    private Long customerId;
    private Long vendorId;
    private Instant orderDate;
    private Long orderTotalAmount;
    private String orderStatus;
}
