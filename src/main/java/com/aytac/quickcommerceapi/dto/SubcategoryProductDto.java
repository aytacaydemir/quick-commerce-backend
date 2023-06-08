package com.aytac.quickcommerceapi.dto;

import java.util.List;

public record SubcategoryProductDto(
        Long id,
        String title,
        List<ProductSummaryDto> products
) {
}