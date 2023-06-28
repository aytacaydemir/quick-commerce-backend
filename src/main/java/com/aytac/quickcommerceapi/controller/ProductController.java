package com.aytac.quickcommerceapi.controller;

import com.aytac.quickcommerceapi.dto.ProductDto;
import com.aytac.quickcommerceapi.dto.request.ProductCreateRequest;
import com.aytac.quickcommerceapi.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Get Product Details by Product Id")
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long productId) {

        return ResponseEntity.ok(productService.getProductById(productId));
    }

    @Operation(summary = "Create Product")
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductCreateRequest request) {
        return ResponseEntity.ok(productService.createProduct(request));
    }
}
