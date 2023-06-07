package com.aytac.quickcommerceapi.controller;

import com.aytac.quickcommerceapi.dto.PaginatedDataDto;
import com.aytac.quickcommerceapi.dto.request.CategoryCreateRequest;
import com.aytac.quickcommerceapi.dto.response.ApiResponse;
import com.aytac.quickcommerceapi.dto.request.CategoryUpdateRequest;
import com.aytac.quickcommerceapi.dto.CategoryDto;
import com.aytac.quickcommerceapi.dto.CategorySubcategoriesDto;
import com.aytac.quickcommerceapi.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping
    @Operation(
            summary = "Get all categories",
            description = "Retrieves list of all categories without subcategories"
    )
    public ResponseEntity<ApiResponse<PaginatedDataDto<CategoryDto>>> getAllCategories
            (@RequestParam(defaultValue = "0") int page,
             @RequestParam(defaultValue = "5") int size) {
        PaginatedDataDto<CategoryDto> categories = categoryService.getAllCategories(page, size);

        return new ResponseEntity<>(
                new ApiResponse<>(true, categories, null), HttpStatus.OK);

    }

    @GetMapping("/subcategories")
    @Operation(
            summary = "Get all categories with subcategories",
            description = "Retrieves list of all categories without subcategories"
    )
    public ResponseEntity<ApiResponse<List<CategorySubcategoriesDto>>> getCategoriesWithSubcategories(){
        return new ResponseEntity<>(
                new ApiResponse<>(true, categoryService.getCategoriesWithSubcategories(), null), HttpStatus.OK);
    }

/*
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> getCategoryById(
            @PathVariable(required = false) Long id) {

        CategoryResponse categoryResponseDto = categoryService.getCategoryById(id);
        if (categoryResponseDto != null) {
            return new ResponseEntity<>(
                    new ApiResponse<>(true, categoryResponseDto, null), HttpStatus.OK);
        }
        return new ResponseEntity<>(
                new ApiResponse<>(false, null, "404 - Category Not Found"), HttpStatus.NOT_FOUND);
    }*/
/*
    @GetMapping("/{categoryId}")
    public CategorySubcategoriesDto getCategoryWithProductSummary(@PathVariable Long categoryId){
        return categoryService.getSome(categoryId);
    }*/



    @PostMapping
    public ResponseEntity<ApiResponse<CategoryDto>> postCategory(@RequestBody CategoryCreateRequest request) {

        return new ResponseEntity<>(
                new ApiResponse<>(true, categoryService.createCategory(request), null), HttpStatus.CREATED);
    }
/*
    @PostMapping("/{id}/subcategory")
    public ResponseEntity<ApiResponse<Subcategory>> createSubcategory(@PathVariable Long id, SubcategoryCreateRequest request){
        Subcategory subcategory = categoryService.createSubcategory(id, request);
        return new ResponseEntity<>(
                new ApiResponse<>(true, subcategory, null), HttpStatus.CREATED);
    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryDto>> deleteCategoryById(@PathVariable Long id) {
        if (categoryService.deleteCategoryById(id)) {
            return new ResponseEntity<>(
                    new ApiResponse<>(true, null, null), HttpStatus.OK);
        }
        return new ResponseEntity<>(
                new ApiResponse<>(false, null, "404 - Category Not Found"), HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryDto>> updateCategoryById(
            @RequestBody CategoryUpdateRequest request, @PathVariable Long id) {

        CategoryDto dto = categoryService.updateCategoryById(request, id);

        if (dto != null) {
            return new ResponseEntity<>(
                    new ApiResponse<>(true, dto, null), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(
                new ApiResponse<>(false, null, "404 - Category Not Found"), HttpStatus.NOT_FOUND);

    }
}
