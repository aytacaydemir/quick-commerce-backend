package com.aytac.quickcommerceapi.controller;

import com.aytac.quickcommerceapi.dto.PaginatedDataDto;
import com.aytac.quickcommerceapi.dto.response.ApiResponse;
import com.aytac.quickcommerceapi.dto.request.CategoryUpdateRequest;
import com.aytac.quickcommerceapi.dto.response.CategoryResponse;
import com.aytac.quickcommerceapi.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(value = {"", "/"})
    public ResponseEntity<ApiResponse<PaginatedDataDto<CategoryResponse>>> getAllCategories
            (@RequestParam(defaultValue = "0") int page,
             @RequestParam(defaultValue = "5") int size) {
        PaginatedDataDto<CategoryResponse> categories = categoryService.getAllCategories(page, size);

        return new ResponseEntity<>(new ApiResponse<>(true, categories, null), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> getCategoryById(@PathVariable(required = false) Long id) {

        CategoryResponse categoryResponseDto = categoryService.getCategoryById(id);
        if (categoryResponseDto != null) {
            return new ResponseEntity<>(new ApiResponse<>(true, categoryResponseDto, null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse<>(false, null, "404 - Category Not Found"), HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public void postCategory(@RequestParam String title, @RequestParam String image) {
        categoryService.createCategory(title, image);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteCategoryById(@PathVariable Long id) {
        if (categoryService.deleteCategoryById(id)) {
            return new ResponseEntity<>(new ApiResponse<>(true, null, null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse<>(false, null, "404 - Category Not Found"), HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> updateCategoryById(@RequestBody CategoryUpdateRequest request, @PathVariable Long id) {

        CategoryResponse dto = categoryService.updateCategoryById(request, id);

        if (dto != null) {
            return new ResponseEntity<>(new ApiResponse<>(true, dto, null), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new ApiResponse<>(false, null, "404 - Category Not Found"), HttpStatus.NOT_FOUND);

    }
}
