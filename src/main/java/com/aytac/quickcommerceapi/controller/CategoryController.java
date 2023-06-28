package com.aytac.quickcommerceapi.controller;

import com.aytac.quickcommerceapi.dto.CategoryDto;
import com.aytac.quickcommerceapi.dto.CategorySubcategoryDto;
import com.aytac.quickcommerceapi.dto.CategorySubcategoryProductDto;
import com.aytac.quickcommerceapi.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(summary = "Get all categories")
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @Operation(summary = "Get all categories with subcategories without products")
    @GetMapping("/subcategories")
    public ResponseEntity<List<CategorySubcategoryDto>> getAllCategoriesWithSubcategories() {
        return ResponseEntity.ok(categoryService.getAllCategoriesWithSubcategories());
    }

    @Operation(summary = "Get all products associated with a particular category.")
    @GetMapping(value = {"/{categoryId}/products"})
    public ResponseEntity<CategorySubcategoryProductDto> getCategoryWithSubcategoriesAndProducts
            (@PathVariable Long categoryId) {

        return ResponseEntity.ok(categoryService.getCategoryWithSubcategoriesAndProduct(categoryId));
    }
}
