package com.aytac.quickcommerceapi.service;

import com.aytac.quickcommerceapi.dto.ProductAttributeDto;
import com.aytac.quickcommerceapi.dto.converter.ProductAttributeDtoConverter;
import com.aytac.quickcommerceapi.dto.request.ProductAttributeCreateRequest;
import com.aytac.quickcommerceapi.exception.custom.AttributeAlreadyExistException;
import com.aytac.quickcommerceapi.model.Attribute;
import com.aytac.quickcommerceapi.model.Product;
import com.aytac.quickcommerceapi.model.ProductAttribute;
import com.aytac.quickcommerceapi.repository.ProductAttributeRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductAttributeService {

    private final ProductAttributeRepository productAttributeRepository;
    private final AttributeService attributeService;
    private final ProductService productService;
    private final ProductAttributeDtoConverter converter;

    public ProductAttributeService(ProductAttributeRepository productAttributeRepository,
                                   AttributeService attributeService,
                                   ProductService productService,
                                   ProductAttributeDtoConverter converter) {
        this.productAttributeRepository = productAttributeRepository;
        this.attributeService = attributeService;
        this.productService = productService;
        this.converter = converter;
    }

    public ProductAttributeDto createProductAttribute(Long productId, ProductAttributeCreateRequest request) {

        Product product = productService.findProductById(productId);
        Attribute attribute = attributeService.getAttributeById(request.attributeId());

        boolean isAttributeExist = product.getProductAttributes().stream()
                .map(ProductAttribute::getAttribute).anyMatch(j -> j.equals(attribute));

        if (isAttributeExist) {
            throw new AttributeAlreadyExistException("This attribute=(" + attribute.getTitle()
                    + ") is already exist in this product=(" + product.getTitle() + ")");
        } else {
            ProductAttribute toSave = new ProductAttribute();
            toSave.setProduct(product);
            toSave.setAttribute(attribute);
            toSave.setDescription(request.description());

            productAttributeRepository.save(toSave);

            return converter.convert(toSave);
        }
    }

}
