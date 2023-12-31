package com.example.OrderService.exception;

import com.example.OrderService.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(OrderException.class)
    public ResponseEntity<ErrorResponse> handleProductServiceException(OrderException orderException){

        return new ResponseEntity<>(new ErrorResponse().builder()
                .errorMessage(orderException.getMessage())
                .errorCode(orderException.getErrorCode())
                .build(), HttpStatus.NOT_FOUND);
    }
}
