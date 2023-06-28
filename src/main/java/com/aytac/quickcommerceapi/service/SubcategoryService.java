package com.aytac.quickcommerceapi.service;

import com.aytac.quickcommerceapi.exception.SubcategoryNotFoundException;
import com.aytac.quickcommerceapi.model.Subcategory;
import com.aytac.quickcommerceapi.repository.SubcategoryRepository;
import org.springframework.stereotype.Service;


@Service
public class SubcategoryService {

    private final SubcategoryRepository subcategoryRepository;

    public SubcategoryService(SubcategoryRepository subcategoryRepository) {
        this.subcategoryRepository = subcategoryRepository;
    }

    public Subcategory getSubcategoryById(Long id) {
        return subcategoryRepository.findById(id)
                .orElseThrow(() -> new SubcategoryNotFoundException("Subcategory not found by id= " + id));
    }
}
