package com.aytac.quickcommerceapi.controller;

import com.aytac.quickcommerceapi.dto.request.SubcategoryCreateRequest;
import com.aytac.quickcommerceapi.dto.response.ApiResponse;
import com.aytac.quickcommerceapi.dto.response.SubcategoryResponse;
import com.aytac.quickcommerceapi.service.SubcategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subcategories")
public class SubcategoryController {

    private final SubcategoryService subcategoryService;

    public SubcategoryController(SubcategoryService subcategoryService) {
        this.subcategoryService = subcategoryService;
    }


    @PostMapping("/{categoryId}")
    public ResponseEntity<ApiResponse<SubcategoryResponse>> createSubcategory(
            @PathVariable Long categoryId, @RequestBody SubcategoryCreateRequest request){

        return new ResponseEntity<>(
                new ApiResponse<>(true, subcategoryService.createSubcategory(categoryId, request), null), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SubcategoryResponse>> getSubcategoryById(@PathVariable Long id){

        return new ResponseEntity<>(
                new ApiResponse<>(true, subcategoryService.getSubcategoryById(id), null), HttpStatus.OK);
    }
}