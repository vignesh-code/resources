package com.coeus.controllers.product;

import com.coeus.responses.product.ProductPageResponse;
import com.coeus.helpers.graphql.GraphQLHelper;
import com.coeus.models.product.ProductEntity;
import com.coeus.requests.product.FetchProductRequest;
import com.coeus.requests.product.UpdateProductPricingRequest;
import com.coeus.responses.product.ProductResponse;
import com.coeus.services.product.ProductService;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @QueryMapping
    public ProductResponse fetchProductById(@Argument(name = "id") String id) {

        log.info("received a request to fetch product {}", id);

        ProductEntity product = productService.fetchProductById(id);
        log.info("found a product {} successfully", product.getId());

        return transform(product);
    }

    @QueryMapping
    public ProductPageResponse fetchProducts(DataFetchingEnvironment environment,
                                             @Argument(name = "page") Integer page,
                                             @Argument(name = "limit") Integer limit) {

        log.info("received a request to fetch products page {} limit {}", page, limit);

        FetchProductRequest fetchRequest = createFetchRequest(environment, page, limit);

        Page<ProductEntity> result = productService.fetchProducts(fetchRequest);

        List<ProductResponse> products = result.getContent().stream()
                .map(this::transform)
                .collect(Collectors.toList());

        log.info("found {} products on page {}", products.size(), page);

        ProductPageResponse response = new ProductPageResponse(page, limit);
        response.setRecords(products);
        response.setTotalPages(result.getTotalPages());
        response.setTotalRecordsCount(result.getTotalElements());

        return response;
    }

    private FetchProductRequest createFetchRequest(DataFetchingEnvironment environment, int page, int limit) {

        List<String> queryFields = GraphQLHelper.getNestedFields(environment, "records");

        return FetchProductRequest.builder()
                .queryFields(queryFields)
                .page(page)
                .limit(limit)
                .sortField("name")
                .sortDirection(Sort.Direction.ASC)
                .build();
    }

    private ProductResponse transform(ProductEntity product) {

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .category(product.getCategory())
                .unit(product.getUnit())
                .build();
    }

    @MutationMapping
    public ProductResponse updateProductPricing(@Argument(name = "id") String id,
                                                @Argument(name = "updateProductPricing") UpdateProductPricingRequest updateRequest) {

        log.info("received a request to update product {} pricing", id);

        ProductEntity updated = productService.updateProductPricing(id, updateRequest);

        return transform(updated);
    }

    @MutationMapping
    public String deleteProductById(@Argument(name = "id") String id) {

        log.info("received a request to delete product {}", id);

        productService.deleteProductById(id);
        log.info("deleted product {} successfully", id);

        return id;
    }
}
