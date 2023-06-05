package com.aytac.quickcommerceapi.dto;

public record MetaDto(
        int page,
        int take,
        Long itemCount,
        int pageCount,
        boolean hasPreviousPage,
        boolean hasNextPage
) {
}
