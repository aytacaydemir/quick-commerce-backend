package com.aytac.quickcommerceapi.service;

import com.aytac.quickcommerceapi.dto.MetaDto;
import com.aytac.quickcommerceapi.dto.converter.CategoryResponseConverter;
import com.aytac.quickcommerceapi.dto.converter.MetaDtoConverter;
import com.aytac.quickcommerceapi.dto.request.CategoryUpdateRequest;
import com.aytac.quickcommerceapi.dto.PaginatedDataDto;
import com.aytac.quickcommerceapi.dto.response.CategoryResponse;
import com.aytac.quickcommerceapi.model.Category;
import com.aytac.quickcommerceapi.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryResponseConverter converter;
    private final MetaDtoConverter metaConverter;

    public CategoryService(CategoryRepository categoryRepository,
                           CategoryResponseConverter converter,
                            MetaDtoConverter metaConverter) {
        this.categoryRepository = categoryRepository;
        this.converter = converter;
        this.metaConverter = metaConverter;
    }

    public PaginatedDataDto<CategoryResponse> getAllCategories(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Category> categories = categoryRepository.findAll(pageable);

        MetaDto metaDto = metaConverter.convert(categories);

       // return categories.map(converter::convert);
        return new PaginatedDataDto<CategoryResponse>(
                (categories.getContent().stream().map(converter::convert)).collect(Collectors.toList()),
                metaDto);
    }

    public CategoryResponse getCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);

        return category.map(converter::convert).orElse(null);
    }

    public void createCategory(String title, String image) {
        Category category = new Category();
        category.setTitle(title);
        category.setImage(image);
        categoryRepository.save(category);
    }

    public boolean deleteCategoryById(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public CategoryResponse updateCategoryById(CategoryUpdateRequest request, Long id) {
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
}
