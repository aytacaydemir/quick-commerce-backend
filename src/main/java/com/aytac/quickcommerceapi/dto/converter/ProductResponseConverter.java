package com.aytac.quickcommerceapi.dto.converter;

import com.aytac.quickcommerceapi.dto.ProductAttributeDto;
import com.aytac.quickcommerceapi.dto.ProductDto;
import com.aytac.quickcommerceapi.model.Product;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ProductResponseConverter {

    public ProductDto convert(Product product) {
        return new ProductDto(
                product.getId(),
                product.getTitle(),
                product.getAltText(),
                product.getImageData(),
                product.getPrice(),
                product.getProductAttributes()
                        .stream()
                        .map(i -> new ProductAttributeDto(i.getAttribute().getTitle(), i.getDescription()))
                        .collect(Collectors.toList()),
                product.getSubcategory().getId()
        );
    }
}
