package com.aytac.quickcommerceapi.controller;

import com.aytac.quickcommerceapi.dto.ProductDto;
import com.aytac.quickcommerceapi.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{productId}")
    public ProductDto getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }
}
