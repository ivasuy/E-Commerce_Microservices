package com.vasu.CustomerService.exception;

import com.vasu.CustomerService.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomerException.class)
    public ResponseEntity<ErrorResponse> handleCustomerServiceException(CustomerException customerException){

        return new ResponseEntity<>(new ErrorResponse().builder()
                .errorMessage(customerException.getMessage())
                .errorCode(customerException.getErrorCode())
                .build(), HttpStatus.NOT_FOUND);

    }
}
