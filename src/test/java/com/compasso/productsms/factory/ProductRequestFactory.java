package com.compasso.productsms.factory;

import com.compasso.productsms.resource.product.request.ProductRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductRequestFactory implements Factory<ProductRequest> {

    private final static long ID_PRODUCT = 2;

    @Override
    public ProductRequest createDefault() {
        return ProductRequest.builder()
                             .id(ID_PRODUCT)
                             .name("course java with spring boot")
                             .description("spring framework for beginners with spring boot")
                             .price(BigDecimal.valueOf(3000.00))
                             .build();
    }
}
