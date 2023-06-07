package com.aytac.quickcommerceapi.dto.response;

import com.aytac.quickcommerceapi.dto.ProductAttributeDto;
import java.math.BigDecimal;
import java.util.List;

public record ProductResponse(
        Long id,
        String title,
        String altText,
        String imageData,
        BigDecimal price,
        List<ProductAttributeDto> productAttributes,
        Long subcategoryId
) {
}
