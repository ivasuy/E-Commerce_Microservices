package com.example.OrderService.exception;

import lombok.Data;

@Data
public class OrderException extends RuntimeException{

    private String errorCode;

    public OrderException(String errorMessage, String errorCode){
        super(errorMessage);
        this.errorCode = errorCode;
    }
}
