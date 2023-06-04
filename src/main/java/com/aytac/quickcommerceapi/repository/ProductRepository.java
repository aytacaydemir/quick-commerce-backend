package com.aytac.quickcommerceapi.repository;

import com.aytac.quickcommerceapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
