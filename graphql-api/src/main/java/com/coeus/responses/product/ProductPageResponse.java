package com.coeus.responses.product;

import com.coeus.responses.common.BasePageResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductPageResponse extends BasePageResponse {

    private List<ProductResponse> records;

    public ProductPageResponse(int page, int limit) {
        super(page, limit);
    }
}
