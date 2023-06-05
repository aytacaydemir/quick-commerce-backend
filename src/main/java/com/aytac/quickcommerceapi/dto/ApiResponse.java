package com.aytac.quickcommerceapi.dto;

public record ApiResponse<T>(
        boolean isSuccessful,
        T data,
        String errorCode
) {
    public ApiResponse(boolean isSuccessful, T data, String errorCode) {
        this.isSuccessful = isSuccessful;
        this.data = data;
        this.errorCode = errorCode;
    }
}
