package com.aytac.quickcommerceapi.dto.converter;

import com.aytac.quickcommerceapi.dto.response.SubcategoryResponse;
import com.aytac.quickcommerceapi.model.Subcategory;
import org.springframework.stereotype.Component;

@Component
public class SubcategoryResponseConverter {

    public SubcategoryResponse convert(Subcategory subcategory) {
        return new SubcategoryResponse(
                subcategory.getId(),
                subcategory.getTitle(),
                subcategory.getCategory().getId());
    }
}
