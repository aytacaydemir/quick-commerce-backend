package com.aytac.quickcommerceapi.dto.converter;

import com.aytac.quickcommerceapi.dto.response.CategoryResponse;
import com.aytac.quickcommerceapi.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryResponseConverter {

    public CategoryResponse convert(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getTitle(),
                category.getImage());
    }
}
