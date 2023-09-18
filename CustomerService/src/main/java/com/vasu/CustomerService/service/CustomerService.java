package com.vasu.CustomerService.service;

import com.vasu.CustomerService.model.CustomerRequest;
import com.vasu.CustomerService.model.CustomerResponse;

public interface CustomerService  {
    String createCustomer(CustomerRequest customerRequest);

    CustomerResponse getCustomerById(Long customerId);

    String updateCustomer(Long customerId, CustomerRequest customerRequest);

    String deleteCustomer(Long customerId);
}
