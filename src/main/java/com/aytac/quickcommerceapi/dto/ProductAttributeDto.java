package com.aytac.quickcommerceapi.dto;

import com.aytac.quickcommerceapi.model.Attribute;

public record ProductAttributeDto(
        Long id,
        String description,
        Attribute attribute
) {
}