package com.aytac.quickcommerceapi.service;

import com.aytac.quickcommerceapi.exception.custom.AttributeNotFoundException;
import com.aytac.quickcommerceapi.model.Attribute;
import com.aytac.quickcommerceapi.repository.AttributeRepository;
import org.springframework.stereotype.Service;

@Service
public class AttributeService {

    private final AttributeRepository attributeRepository;

    public AttributeService(AttributeRepository attributeRepository) {
        this.attributeRepository = attributeRepository;
    }

    public Attribute getAttributeById(Long id) {
        return attributeRepository.findById(id)
                .orElseThrow(() -> new AttributeNotFoundException("Attribute not found by id= " + id));
    }
}
