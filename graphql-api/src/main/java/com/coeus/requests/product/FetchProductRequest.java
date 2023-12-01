package com.coeus.requests.product;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@Getter
@Setter
@Builder
public class FetchProductRequest {

    private List<String> queryFields;

    private int page;
    private int limit;

    private String sortField;
    private Sort.Direction sortDirection;

    public Sort getSort() {
        return Sort.by(sortDirection, sortField);
    }

    public Pageable getPageable() {
        return PageRequest.of(page, limit);
    }
}
