package com.example.ItemService.exception;

import com.example.ItemService.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ItemException.class)
    public ResponseEntity<ErrorResponse> handleProductServiceException(ItemException itemException){

        return new ResponseEntity<>(new ErrorResponse().builder()
                .errorMessage(itemException.getMessage())
                .errorCode(itemException.getErrorCode())
                .build(), HttpStatus.NOT_FOUND);
    }
}
