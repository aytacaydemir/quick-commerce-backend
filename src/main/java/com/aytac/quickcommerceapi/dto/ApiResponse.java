package com.aytac.quickcommerceapi.dto;

public record ApiResponse<T>(
        boolean isSuccessful,
        T data,
        String errorCode
) {
}
