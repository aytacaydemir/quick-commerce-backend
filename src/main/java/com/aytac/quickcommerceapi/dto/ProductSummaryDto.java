package com.aytac.quickcommerceapi.dto;

import java.math.BigDecimal;

public record ProductSummaryDto(
        Long id,
        String shortTitle,
        String altText,
        String imageData,
        BigDecimal price
) {
}
