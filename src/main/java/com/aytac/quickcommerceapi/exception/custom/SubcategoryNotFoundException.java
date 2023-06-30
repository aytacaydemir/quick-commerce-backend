package com.aytac.quickcommerceapi.exception.custom;

public class SubcategoryNotFoundException extends RuntimeException{

    public SubcategoryNotFoundException(String message){
        super(message);
    }
}
