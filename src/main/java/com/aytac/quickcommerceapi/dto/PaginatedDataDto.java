package com.aytac.quickcommerceapi.dto;

import java.util.List;

public record PaginatedDataDto<T>(
        List<T> content,
        MetaDto meta
) {
}
