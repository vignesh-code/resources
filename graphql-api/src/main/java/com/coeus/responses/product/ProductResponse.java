package com.coeus.responses.product;

import com.coeus.models.product.ProductCategory;
import com.coeus.models.product.ProductUnit;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductResponse {

    private String id;
    private String name;
    private ProductCategory category;
    private ProductUnit unit;

}
