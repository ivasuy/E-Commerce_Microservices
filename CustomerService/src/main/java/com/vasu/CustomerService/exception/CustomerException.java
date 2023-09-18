package com.vasu.CustomerService.exception;

import lombok.Data;

@Data
public class CustomerException extends RuntimeException{

    private String errorCode;

    public CustomerException(String errorMessage, String errorCode){
        super(errorMessage);
        this.errorCode = errorCode;
    }

}
