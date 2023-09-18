package com.example.ItemService.exception;

import lombok.Data;

@Data
public class ItemException extends RuntimeException{

    private String errorCode;

    public ItemException(String errorMessage, String errorCode){
        super(errorMessage);
        this.errorCode = errorCode;
    }
}
