package com.aytac.quickcommerceapi.exception.custom;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String message) {
        super(message);
    }
}
