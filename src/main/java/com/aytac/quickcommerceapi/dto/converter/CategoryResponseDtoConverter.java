package com.aytac.quickcommerceapi.dto.converter;

import com.aytac.quickcommerceapi.dto.response.CategoryResponseDto;
import com.aytac.quickcommerceapi.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryResponseDtoConverter {

    public CategoryResponseDto convert(Category category) {
        return new CategoryResponseDto(
                category.getId(),
                category.getTitle(),
                category.getImage());
    }
}
