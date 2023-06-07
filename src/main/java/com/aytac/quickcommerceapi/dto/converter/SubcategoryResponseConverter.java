package com.aytac.quickcommerceapi.dto.converter;

import com.aytac.quickcommerceapi.dto.SubcategoryDto;
import com.aytac.quickcommerceapi.model.Subcategory;
import org.springframework.stereotype.Component;

@Component
public class SubcategoryResponseConverter {

    public SubcategoryDto convert(Subcategory subcategory) {
        return new SubcategoryDto(
                subcategory.getId(),
                subcategory.getTitle());
    }
}
