package com.coeus.models.product;

import com.coeus.models.common.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = ProductEntity.COLLECTION_NAME)
public class ProductEntity extends BaseEntity {

    public static final String COLLECTION_NAME = "products";

    private String name;
    private String description;

    @Field("purchasing_price")
    private Double purchasingPrice;

    @Field("selling_price")
    private Double sellingPrice;

    private ProductCategory category;
    private ProductUnit unit;

}
