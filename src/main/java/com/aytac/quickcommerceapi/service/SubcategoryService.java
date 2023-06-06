package com.aytac.quickcommerceapi.service;

import com.aytac.quickcommerceapi.dto.converter.SubcategoryResponseConverter;
import com.aytac.quickcommerceapi.dto.request.SubcategoryCreateRequest;
import com.aytac.quickcommerceapi.dto.request.SubcategoryUpdateRequest;
import com.aytac.quickcommerceapi.dto.response.SubcategoryResponse;
import com.aytac.quickcommerceapi.model.Category;
import com.aytac.quickcommerceapi.model.Subcategory;
import com.aytac.quickcommerceapi.repository.SubcategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubcategoryService {

    private final CategoryService categoryService;
    private final SubcategoryRepository subcategoryRepository;
    private final SubcategoryResponseConverter converter;

    public SubcategoryService(SubcategoryRepository subcategoryRepository,
                              CategoryService categoryService,
                              SubcategoryResponseConverter converter) {
        this.subcategoryRepository = subcategoryRepository;
        this.categoryService = categoryService;
        this.converter = converter;
    }

    public List<Subcategory> getSubcategoriesByCategoryId(Long id) {
        return subcategoryRepository.findByCategoryId(id);
    }

    public Subcategory findSubcategoryById(Long id) {
        return subcategoryRepository.findById(id).orElse(null);
    }

    public SubcategoryResponse getSubcategoryById(Long id) {
        Optional<Subcategory> subcategory = subcategoryRepository.findById(id);
        return subcategory.map(converter::convert).orElse(null);
    }

    public SubcategoryResponse createSubcategory(Long categoryId, SubcategoryCreateRequest request) {

        Optional<Category> category = categoryService.findCategoryById(categoryId);

        if (category.isPresent()) {
            Subcategory toSave = new Subcategory();
            toSave.setTitle(request.title());
            toSave.setCategory(category.get());
            subcategoryRepository.save(toSave);
            return converter.convert(toSave);
        }
        return null;
    }

    public Subcategory updateSubcategory(Long id, SubcategoryUpdateRequest request) {
        Optional<Subcategory> subcategory = subcategoryRepository.findById(id);
        if (subcategory.isPresent()) {
            Subcategory toUpdate = subcategory.get();
            toUpdate.setTitle(request.title());
        }
        return null;
    }
}
