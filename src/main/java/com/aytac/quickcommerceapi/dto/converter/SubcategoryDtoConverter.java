package com.aytac.quickcommerceapi.dto.converter;

import com.aytac.quickcommerceapi.dto.SubcategoryDto;
import com.aytac.quickcommerceapi.dto.SubcategoryProductDto;
import com.aytac.quickcommerceapi.model.Subcategory;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class SubcategoryDtoConverter {

    private final ProductDtoConverter productDtoConverter;

    public SubcategoryDtoConverter(ProductDtoConverter productDtoConverter) {
        this.productDtoConverter = productDtoConverter;
    }

    public SubcategoryDto convertToSubcategoryDto(Subcategory from) {
        return new SubcategoryDto(from.getId(), from.getTitle());
    }

    public SubcategoryProductDto convertToSubcategoryProductDto(Subcategory from) {
        return new SubcategoryProductDto(
                from.getId(),
                from.getTitle(),
                from.getProducts().stream()
                        .map(productDtoConverter::convertToProductSummaryDto).collect(Collectors.toList()));
    }
}