package com.coeus.services.product;

import com.coeus.models.product.ProductEntity;
import com.coeus.requests.product.FetchProductRequest;
import com.coeus.requests.product.UpdateProductPricingRequest;
import com.coeus.repositories.product.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Slf4j
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductEntity fetchProductById(String id) {
        return productRepository.findById(id).orElse(null);
    }

    public Page<ProductEntity> fetchProducts(FetchProductRequest fetchRequest) {
        log.debug("fetching products on page {} {}", fetchRequest.getPage(), fetchRequest.getLimit());

        return productRepository.fetchProducts(fetchRequest);
    }

    public ProductEntity updateProductPricing(String id, UpdateProductPricingRequest updateRequest) {

        ProductEntity existing = fetchProductById(id);
        if (ObjectUtils.isEmpty(existing)) {
            return null;
        }

        return productRepository.updateProductPricing(existing, updateRequest);
    }

    public String deleteProductById(String id) {

        log.debug("deleting product {}", id);

        productRepository.deleteById(id);

        log.info("deleted product {} successfully", id);

        return id;
    }
}
