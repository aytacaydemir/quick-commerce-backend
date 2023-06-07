package com.aytac.quickcommerceapi.dto;

import java.math.BigDecimal;
import java.util.List;

public record ProductDto(
        Long id,
        String title,
        String altText,
        String imageData,
        BigDecimal price,
        List<ProductAttributeDto> productAttributes,
        Long subcategoryId
) {
}
