package com.aytac.quickcommerceapi.dto;

import java.util.List;

public record SubcategoryProductSummaryDto(
        Long id,
        String title,
        List<ProductSummaryDto> products
) {
}
