package com.aytac.quickcommerceapi.dto.converter;

import com.aytac.quickcommerceapi.dto.ProductAttributeDto;
import com.aytac.quickcommerceapi.model.ProductAttribute;
import org.springframework.stereotype.Component;

@Component
public class ProductAttributeDtoConverter {

    public ProductAttributeDto convert(ProductAttribute productAttribute){
        return new ProductAttributeDto(
          productAttribute.getAttribute().getTitle(),
          productAttribute.getDescription()
        );
    }
}
