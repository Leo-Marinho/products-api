package com.compasso.productsms.resource.product.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductFilterDTO {

    private String criteriaFilter;
    private BigDecimal minimumPrice;
    private BigDecimal maximumPrice;
}
