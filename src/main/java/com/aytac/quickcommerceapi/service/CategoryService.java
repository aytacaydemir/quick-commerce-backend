package com.aytac.quickcommerceapi.service;

import com.aytac.quickcommerceapi.dto.MetaDto;
import com.aytac.quickcommerceapi.dto.converter.CategoryResponseConverter;
import com.aytac.quickcommerceapi.dto.converter.MetaDtoConverter;
import com.aytac.quickcommerceapi.dto.request.CategoryCreateRequest;
import com.aytac.quickcommerceapi.dto.request.CategoryUpdateRequest;
import com.aytac.quickcommerceapi.dto.PaginatedDataDto;
import com.aytac.quickcommerceapi.dto.CategoryDto;
import com.aytac.quickcommerceapi.dto.CategorySubcategoriesDto;
import com.aytac.quickcommerceapi.model.Category;
import com.aytac.quickcommerceapi.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryResponseConverter converter;
    private final MetaDtoConverter metaConverter;
    private final SubcategoryService subcategoryService;

    public CategoryService(CategoryRepository categoryRepository,
                           CategoryResponseConverter converter,
                           MetaDtoConverter metaConverter,
                           SubcategoryService subcategoryService) {
        this.categoryRepository = categoryRepository;
        this.converter = converter;
        this.metaConverter = metaConverter;
        this.subcategoryService = subcategoryService;
    }

    public PaginatedDataDto<CategoryDto> getAllCategories(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Category> categories = categoryRepository.findAll(pageable);

        MetaDto metaDto = metaConverter.convert(categories);

        return new PaginatedDataDto<CategoryDto>(
                (categories.getContent().stream().map(converter::convert)).collect(Collectors.toList()),
                metaDto);
    }

    public CategoryDto getCategoryById(Long id) {

        return converter.convert(findCategoryById(id));
    }

    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null); //exception
    }



/*
    public CategorySubcategoriesDto getSome(Long categoryId) {
        Category category = findCategoryById(categoryId);

        return new CategorySubcategoriesDto(
                category.getId(),
                category.getTitle(),
                category.getImage(),
                category.getSubcategories().stream().map(i -> new SubcategoryProductSummaryDto(
                        i.getId(),
                        i.getTitle(),
                        i.getId(),
                        i.getProducts().stream().map(j -> new ProductSummaryDto(
                                j.getId(),
                                j.getShortTitle(),
                                j.getAltText(),
                                j.getImageData(),
                                j.getPrice()
                        )).collect(Collectors.toList())
                )).collect(Collectors.toList())
        );
    }*/

    public CategoryDto createCategory(CategoryCreateRequest request) {
        Category category = new Category();
        category.setTitle(request.title());
        category.setImage(request.image());

        return converter.convert(categoryRepository.save(category));
    }

    public boolean deleteCategoryById(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public CategoryDto updateCategoryById(CategoryUpdateRequest request, Long id) {
        Optional<Category> category = categoryRepository.findById(id);

        if (category.isPresent()) {
            Category categoryUpdate = category.get();
            categoryUpdate.setTitle(request.title());
            categoryUpdate.setImage(request.image());

            categoryRepository.save(categoryUpdate);
            return converter.convert(categoryUpdate);
        }
        return null;
    }

    public List<CategorySubcategoriesDto> getCategoriesWithSubcategories() {

        List<Category> categories = categoryRepository.findAll();

        return categories.stream().map(c -> new CategorySubcategoriesDto(
                c.getId(),
                c.getTitle(),
                subcategoryService.getSubcategoriesByCategoryId(c.getId())
        )).collect(Collectors.toList());


    }
}
