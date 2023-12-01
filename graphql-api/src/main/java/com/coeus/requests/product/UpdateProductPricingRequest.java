package com.coeus.requests.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductPricingRequest {

    private Double purchasingPrice;
    private Double sellingPrice;

}
