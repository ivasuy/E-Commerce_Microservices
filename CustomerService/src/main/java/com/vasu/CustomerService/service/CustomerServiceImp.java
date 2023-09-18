package com.vasu.CustomerService.service;

import com.vasu.CustomerService.entity.Customer;
import com.vasu.CustomerService.exception.CustomerException;
import com.vasu.CustomerService.model.CustomerRequest;
import com.vasu.CustomerService.model.CustomerResponse;
import com.vasu.CustomerService.repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;

@Service
public class CustomerServiceImp implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public String createCustomer(CustomerRequest customerRequest) {

        Customer customer = Customer.builder()
                .customerName(customerRequest.getName())
                .customerPhone(customerRequest.getPhone())
                .customerAddress(customerRequest.getAddress())
                .customerEmail(customerRequest.getEmail())
                .customerRegistrationDate(Instant.now())
                .build();

        customerRepository.save(customer);

        return "Customer Created Successfully !";
    }

    @Override
    public CustomerResponse getCustomerById(Long customerId) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerException("Customer with given Id does not exist", "CUSTOMER_NOT_FOUND"));

        CustomerResponse customerResponse = new CustomerResponse();
        BeanUtils.copyProperties(customer, customerResponse);

        return customerResponse;
    }

    @Override
    public String updateCustomer(Long customerId, CustomerRequest customerRequest) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerException("Customer with given Id does not exist", "CUSTOMER_NOT_FOUND"));

        if (customerRequest.getName() != null) {
            customer.setCustomerName(customerRequest.getName());
        }
        if (customerRequest.getEmail() != null) {
            customer.setCustomerEmail(customerRequest.getEmail());
        }
        if (customerRequest.getAddress() != null) {
            customer.setCustomerAddress(customerRequest.getAddress());
        }
        if (customerRequest.getPhone() != null && customerRequest.getPhone() > 0) {
            customer.setCustomerPhone(customerRequest.getPhone());
        }

        customerRepository.save(customer);

        return "Customer Updated Successfully !";
    }

    @Override
    public String deleteCustomer(Long customerId) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerException("Customer with given Id does not exist", "CUSTOMER_NOT_FOUND"));
        customerRepository.delete(customer);

        return "Customer Deleted Successfully !";
    }
}
