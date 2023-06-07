package com.aytac.quickcommerceapi.dto.request;

import java.math.BigDecimal;

public record ProductCreateRequest(
        String title,
        String altText,
        String imageData,
        BigDecimal price
) {
}
