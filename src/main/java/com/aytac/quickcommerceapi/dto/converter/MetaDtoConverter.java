package com.aytac.quickcommerceapi.dto.converter;

import com.aytac.quickcommerceapi.dto.MetaDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class MetaDtoConverter {

    public MetaDto convert(Page<?> from){
        return new MetaDto(
                from.getPageable().getPageNumber(), //page number
                from.getPageable().getPageSize(), //take number
                from.getTotalElements(), //itemCount
                from.getTotalPages(), //total page count
                from.hasPrevious(), //has previous page?
                from.hasNext() //has next page?

        );
    }
}
