package com.coeus.repositories.impl;

import com.coeus.models.product.ProductEntity;
import com.coeus.requests.product.FetchProductRequest;
import com.coeus.requests.product.UpdateProductPricingRequest;
import com.coeus.repositories.custom.ICustomProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Slf4j
@Repository
public class ProductRepositoryImpl implements ICustomProductRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Page<ProductEntity> fetchProducts(FetchProductRequest request) {

        Criteria criteria = new Criteria();

        Query query = new Query(criteria);

        long count = mongoTemplate.count(query, ProductEntity.class);

        query.with(request.getPageable());
        query.with(request.getSort());

        for (String item: request.getQueryFields()) {
            query.fields().include(item);
        }

        List<ProductEntity> products = mongoTemplate.find(query, ProductEntity.class);
        return new PageImpl<>(products, request.getPageable(), count);
    }

    @Override
    public ProductEntity updateProductPricing(ProductEntity product, UpdateProductPricingRequest updateRequest) {

        Criteria criteria = Criteria.where("id").is(new ObjectId(product.getId()));

        Query query = new Query(criteria);

        Update update = new Update();
        update.set("purchasing_price", updateRequest.getPurchasingPrice());
        update.set("selling_price", updateRequest.getSellingPrice());
        update.set("modified_date", new Date());

        return mongoTemplate.findAndModify(query, update, ProductEntity.class);

    }
}
