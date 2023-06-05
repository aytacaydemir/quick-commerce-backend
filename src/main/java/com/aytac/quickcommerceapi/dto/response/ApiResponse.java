package com.aytac.quickcommerceapi.dto.response;

public record ApiResponse<T>(
        boolean isSuccessful,
        T data,
        String errorCode
) {
}
