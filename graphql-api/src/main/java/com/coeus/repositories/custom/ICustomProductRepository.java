package com.coeus.repositories.custom;

import com.coeus.models.product.ProductEntity;
import com.coeus.requests.product.FetchProductRequest;
import com.coeus.requests.product.UpdateProductPricingRequest;
import org.springframework.data.domain.Page;

public interface ICustomProductRepository {

    Page<ProductEntity> fetchProducts(FetchProductRequest fetchRequest);

    ProductEntity updateProductPricing(ProductEntity id, UpdateProductPricingRequest updateRequest);

}
