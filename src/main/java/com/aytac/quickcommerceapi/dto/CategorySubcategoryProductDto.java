package com.aytac.quickcommerceapi.dto;

import java.util.List;

public record CategorySubcategoryProductDto(
        Long id,
        String title,
        List<SubcategoryProductDto> subcategories
) {
}