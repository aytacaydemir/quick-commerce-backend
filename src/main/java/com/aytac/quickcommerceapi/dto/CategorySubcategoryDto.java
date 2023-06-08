package com.aytac.quickcommerceapi.dto;

import java.util.List;

public record CategorySubcategoryDto(
        Long id,
        String title,
        List<SubcategoryDto> subcategories
) {
}
