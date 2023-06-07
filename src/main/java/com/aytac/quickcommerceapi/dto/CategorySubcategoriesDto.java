package com.aytac.quickcommerceapi.dto;

import java.util.List;

//retrieve a list of subcategories for a specific category
public record CategorySubcategoriesDto(
        Long id,
        String title,
        List<SubcategoryDto> subcategories
) {
}
