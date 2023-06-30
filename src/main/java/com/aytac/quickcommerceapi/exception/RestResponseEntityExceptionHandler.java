package com.aytac.quickcommerceapi.exception;

import com.aytac.quickcommerceapi.exception.custom.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {
            CategoryNotFoundException.class,
            ProductNotFoundException.class,
            SubcategoryNotFoundException.class,
            AttributeNotFoundException.class})
    public ResponseEntity<?> notFoundExceptionHandler(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {AttributeAlreadyExistException.class})
    public ResponseEntity<?> badRequestHandler(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
