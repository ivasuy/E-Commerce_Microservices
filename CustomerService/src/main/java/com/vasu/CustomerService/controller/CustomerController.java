package com.vasu.CustomerService.controller;

import com.vasu.CustomerService.model.CustomerRequest;
import com.vasu.CustomerService.model.CustomerResponse;
import com.vasu.CustomerService.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody CustomerRequest customerRequest){

        String customerStatus = customerService.createCustomer(customerRequest);

        return new ResponseEntity<>(customerStatus, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable("id") Long customerId){

        CustomerResponse customerResponse = customerService.getCustomerById(customerId);

        return new ResponseEntity<>(customerResponse, HttpStatus.FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCustomer(
            @PathVariable("id") Long customerId, @RequestBody CustomerRequest customerRequest
    ){

        String customerStatus = customerService.updateCustomer(customerId, customerRequest);

        return new ResponseEntity<>(customerStatus, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") Long customerId){

        String customerStatus = customerService.deleteCustomer(customerId);

        return new ResponseEntity<>(customerStatus, HttpStatus.OK);
    }

}
