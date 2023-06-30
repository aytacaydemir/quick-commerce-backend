package com.aytac.quickcommerceapi.controller;

import com.aytac.quickcommerceapi.dto.ProductAttributeDto;
import com.aytac.quickcommerceapi.dto.request.ProductAttributeCreateRequest;
import com.aytac.quickcommerceapi.repository.ProductAttributeRepository;
import com.aytac.quickcommerceapi.service.ProductAttributeService;
import com.aytac.quickcommerceapi.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product-attribute")
public class ProductAttributeController {

    private final ProductAttributeService productAttributeService;

    public ProductAttributeController(ProductAttributeRepository productAttributeRepository,
                                      ProductService productService,
                                      ProductAttributeService productAttributeService) {
        this.productAttributeService = productAttributeService;
    }

    @Operation(summary = "Create Product Attribute on a Product by Id")
    @PostMapping("/{productId}")
    public ProductAttributeDto createProductAttribute(@PathVariable Long productId,
                                                      @RequestBody ProductAttributeCreateRequest request) {
        return productAttributeService.createProductAttribute(productId, request);
    }

}
