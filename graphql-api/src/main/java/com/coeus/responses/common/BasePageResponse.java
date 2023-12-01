package com.coeus.responses.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasePageResponse  {

    private int currentPage;
    private int recordsPerPage;
    private int totalPages;
    private Long totalRecordsCount;

    public BasePageResponse(int page, int limit) {
        this.currentPage = page;
        this.recordsPerPage = limit;
    }

}
