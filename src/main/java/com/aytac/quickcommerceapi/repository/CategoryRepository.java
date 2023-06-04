package com.aytac.quickcommerceapi.repository;

import com.aytac.quickcommerceapi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
