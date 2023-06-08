package com.aytac.quickcommerceapi.dto.converter;

import com.aytac.quickcommerceapi.dto.ProductDto;
import com.aytac.quickcommerceapi.dto.ProductSummaryDto;
import com.aytac.quickcommerceapi.model.Product;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ProductDtoConverter {

    private final ProductAttributeDtoConverter productAttributeDtoConverter;

    public ProductDtoConverter(ProductAttributeDtoConverter productAttributeDtoConverter) {
        this.productAttributeDtoConverter = productAttributeDtoConverter;
    }

    public ProductDto convertToProductDto(Product from) {
        return new ProductDto(
                from.getId(),
                from.getTitle(),
                from.getAltText(),
                from.getImageData(),
                from.getPrice(),
                from.getProductAttributes()
                        .stream().map(productAttributeDtoConverter::convert).collect(Collectors.toList()));
    }

    public ProductSummaryDto convertToProductSummaryDto(Product from) {
        return new ProductSummaryDto(
                from.getId(),
                from.getShortTitle(),
                from.getAltText(),
                from.getImageData(),
                from.getPrice());
    }
}