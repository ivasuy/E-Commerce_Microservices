package com.vasu.CustomerService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponse {
    private Long customerId;
    private String customerName;
    private String customerEmail;
    private String customerAddress;
    private Long customerPhone;
    private Instant customerRegistrationDate;
}
