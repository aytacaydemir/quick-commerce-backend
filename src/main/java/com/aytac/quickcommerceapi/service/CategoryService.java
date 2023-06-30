package com.aytac.quickcommerceapi.service;

import com.aytac.quickcommerceapi.dto.CategoryDto;
import com.aytac.quickcommerceapi.dto.CategorySubcategoryDto;
import com.aytac.quickcommerceapi.dto.CategorySubcategoryProductDto;
import com.aytac.quickcommerceapi.dto.converter.CategoryDtoConverter;
import com.aytac.quickcommerceapi.exception.custom.CategoryNotFoundException;
import com.aytac.quickcommerceapi.model.Category;
import com.aytac.quickcommerceapi.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryDtoConverter converter;

    public CategoryService(CategoryRepository categoryRepository, CategoryDtoConverter converter) {
        this.categoryRepository = categoryRepository;
        this.converter = converter;
    }

    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream().map(converter::convertToCategoryDto).collect(Collectors.toList());
    }

    public List<CategorySubcategoryDto> getAllCategoriesWithSubcategories() {
        return categoryRepository.findAll().stream()
                .map(converter::convertToCategorySubcategoryDto).collect(Collectors.toList());
    }

    public CategorySubcategoryProductDto getCategoryWithSubcategoriesAndProduct(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found by id=" + categoryId));
        return converter.convertToCategorySubcategoryProductDto(category);
    }
}
