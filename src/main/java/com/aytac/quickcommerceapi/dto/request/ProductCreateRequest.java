package com.aytac.quickcommerceapi.dto.request;

import java.math.BigDecimal;

public record ProductCreateRequest(
        String title,
        String shortTitle,
        String altText,
        String imageData,
        BigDecimal price
) {
}
