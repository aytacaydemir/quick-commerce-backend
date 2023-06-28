package com.aytac.quickcommerceapi.dto.request;

import java.math.BigDecimal;

public record ProductCreateRequest(
        String shortTitle,
        String title,
        String altText,
        String imageData,
        BigDecimal price,
        Long subcategoryId
) {
}
