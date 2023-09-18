package com.vasu.VendorService.exception;

import lombok.Data;

@Data
public class VendorException extends RuntimeException{

    private String errorCode;

    public VendorException(String errorMessage, String errorCode){
        super(errorMessage);
        this.errorCode = errorCode;
    }

}
