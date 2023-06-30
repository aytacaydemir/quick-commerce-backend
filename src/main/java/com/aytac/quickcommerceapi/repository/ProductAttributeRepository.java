package com.aytac.quickcommerceapi.repository;

import com.aytac.quickcommerceapi.model.ProductAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductAttributeRepository extends JpaRepository<ProductAttribute, Long> {
}
