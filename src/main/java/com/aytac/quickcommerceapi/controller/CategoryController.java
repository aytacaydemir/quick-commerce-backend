package com.aytac.quickcommerceapi.controller;

import com.aytac.quickcommerceapi.dto.CategoryDto;
import com.aytac.quickcommerceapi.dto.CategorySubcategoryDto;
import com.aytac.quickcommerceapi.dto.CategorySubcategoryProductDto;
import com.aytac.quickcommerceapi.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/categories/subcategories")
    public ResponseEntity<List<CategorySubcategoryDto>> getAllCategoriesWithSubcategories() {
        return ResponseEntity.ok(categoryService.getAllCategoriesWithSubcategories());
    }

    @GetMapping(value = {"/categories", "/categories/{categoryId}"})
    public ResponseEntity<CategorySubcategoryProductDto> getCategoryWithSubcategoriesAndProducts
            (@PathVariable(required = false) Optional<Long> categoryId) {

        return ResponseEntity.ok(categoryService.getCategoryWithSubcategoriesAndProduct(categoryId));
    }
}
