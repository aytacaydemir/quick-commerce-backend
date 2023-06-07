package com.aytac.quickcommerceapi.service;

import com.aytac.quickcommerceapi.dto.converter.SubcategoryResponseConverter;
import com.aytac.quickcommerceapi.dto.request.SubcategoryCreateRequest;
import com.aytac.quickcommerceapi.dto.request.SubcategoryUpdateRequest;
import com.aytac.quickcommerceapi.dto.SubcategoryDto;
import com.aytac.quickcommerceapi.model.Category;
import com.aytac.quickcommerceapi.model.Subcategory;
import com.aytac.quickcommerceapi.repository.SubcategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<SubcategoryDto> getSubcategoriesByCategoryId(Long id) {
        List<Subcategory> list = subcategoryRepository.findByCategoryId(id);
        return list.stream().map(converter::convert).collect(Collectors.toList());
    }

    public Subcategory findSubcategoryById(Long id) {
        return subcategoryRepository.findById(id).orElse(null);
    }

    public SubcategoryDto getSubcategoryById(Long id) {
        Optional<Subcategory> subcategory = subcategoryRepository.findById(id);
        return subcategory.map(converter::convert).orElse(null);
    }

    public SubcategoryDto createSubcategory(Long categoryId, SubcategoryCreateRequest request) {

        Category category = categoryService.findCategoryById(categoryId);

        if (category != null) {
            Subcategory toSave = new Subcategory();
            toSave.setTitle(request.title());
            toSave.setCategory(category);
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
