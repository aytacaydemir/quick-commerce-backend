package com.aytac.quickcommerceapi.dto.converter;

import com.aytac.quickcommerceapi.dto.CategoryDto;
import com.aytac.quickcommerceapi.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryResponseConverter {

    public CategoryDto convert(Category category) {
        return new CategoryDto(
                category.getId(),
                category.getTitle(),
                category.getImage());
    }
}
