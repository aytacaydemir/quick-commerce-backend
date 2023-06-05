package com.aytac.quickcommerceapi.repository;

import com.aytac.quickcommerceapi.model.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributeRepository extends JpaRepository<Attribute, Long> {
}
