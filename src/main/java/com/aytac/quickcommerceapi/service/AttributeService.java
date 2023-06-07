package com.aytac.quickcommerceapi.service;

import com.aytac.quickcommerceapi.dto.request.AttributeCreateRequest;
import com.aytac.quickcommerceapi.dto.request.AttributeUpdateRequest;
import com.aytac.quickcommerceapi.model.Attribute;
import com.aytac.quickcommerceapi.repository.AttributeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttributeService {

    private final AttributeRepository attributeRepository;

    public AttributeService(AttributeRepository attributeRepository) {
        this.attributeRepository = attributeRepository;
    }

    public List<Attribute> getAllAttributes() {
        return attributeRepository.findAll();
    }

    public Attribute getAttributeById(Long id) {
        return attributeRepository.findById(id).orElse(null);
    }

    public Attribute createAttribute(AttributeCreateRequest request) {
        Attribute attribute = new Attribute();
        attribute.setTitle(request.title());
        return attributeRepository.save(attribute);
    }

    public boolean deleteAttributeById(Long id) {
        boolean isExist = attributeRepository.existsById(id);

        if (isExist) {
            attributeRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Attribute updateAttribute(Long id, AttributeUpdateRequest request) {
        Optional<Attribute> attribute = attributeRepository.findById(id);

        if (attribute.isPresent()) {
            Attribute toUpdate = attribute.get();
            toUpdate.setTitle(request.title());
            attributeRepository.save(toUpdate);
            return toUpdate;
        } else {
            return null;
        }
    }

}
