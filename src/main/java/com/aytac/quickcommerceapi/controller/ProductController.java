package com.aytac.quickcommerceapi.controller;

import com.aytac.quickcommerceapi.dto.request.ProductCreateRequest;
import com.aytac.quickcommerceapi.dto.response.ProductResponse;
import com.aytac.quickcommerceapi.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping("/{subcategoryId}")
    public ProductResponse createProduct(@RequestBody ProductCreateRequest request,
                                         @PathVariable Long subcategoryId) {
        return productService.createProduct(request, subcategoryId);
    }

}
