package com.aytac.quickcommerceapi.dto.converter;

import com.aytac.quickcommerceapi.dto.CategoryDto;
import com.aytac.quickcommerceapi.dto.CategorySubcategoryDto;
import com.aytac.quickcommerceapi.dto.CategorySubcategoryProductDto;
import com.aytac.quickcommerceapi.model.Category;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CategoryDtoConverter {

    private final SubcategoryDtoConverter subcategoryDtoConverter;

    public CategoryDtoConverter(SubcategoryDtoConverter subcategoryDtoConverter) {
        this.subcategoryDtoConverter = subcategoryDtoConverter;
    }

    public CategoryDto convertToCategoryDto(Category from) {
        return new CategoryDto(
                from.getId(),
                from.getTitle(),
                from.getImage()
        );
    }

    public CategorySubcategoryDto convertToCategorySubcategoryDto(Category from) {
        return new CategorySubcategoryDto(
                from.getId(),
                from.getTitle(),
                from.getSubcategories().stream()
                        .map(subcategoryDtoConverter::convertToSubcategoryDto).collect(Collectors.toList())
        );
    }

    public CategorySubcategoryProductDto convertToCategorySubcategoryProductDto(Category from) {
        return new CategorySubcategoryProductDto(
                from.getId(),
                from.getTitle(),
                from.getSubcategories().stream()
                        .map(subcategoryDtoConverter::convertToSubcategoryProductDto).collect(Collectors.toList())
        );
    }
}