package com.compasso.productsms.factory;

import com.compasso.productsms.model.product.ProductEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductFactory implements Factory<ProductEntity> {

    private final static long ID_PRODUCT = 2;

    @Override
    public ProductEntity createDefault() {
        return ProductEntity.builder()
                            .idProduct(ID_PRODUCT)
                            .name("course next level week")
                            .description("course react for development frontend with components")
                            .price(BigDecimal.valueOf(2000.00))
                            .build();
    }
}
