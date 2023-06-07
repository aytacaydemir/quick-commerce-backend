package com.aytac.quickcommerceapi.controller;

import com.aytac.quickcommerceapi.dto.request.ProductCreateRequest;
import com.aytac.quickcommerceapi.dto.ProductDto;
import com.aytac.quickcommerceapi.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/{id}")
    @Operation(
            summary = "Get Product Details By Id",
            description = "Get the specific product information with details by product id")
    public ProductDto getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping("/{subcategoryId}")
    public ProductDto createProduct(@RequestBody ProductCreateRequest request,
                                    @PathVariable Long subcategoryId) {
        return productService.createProduct(request, subcategoryId);
    }

}
