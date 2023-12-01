package com.coeus.repositories.product;

import com.coeus.models.product.ProductEntity;
import com.coeus.repositories.custom.ICustomProductRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<ProductEntity, String>, ICustomProductRepository {
}
