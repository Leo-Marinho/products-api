package com.compasso.productsms.resource.product.request;

import com.compasso.productsms.dto.ProductDTO;
import com.compasso.productsms.utils.FormatadorUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    private String name;
    private String description;
    private BigDecimal price;

    public ProductDTO toDTO() {
        return new ProductDTO(FormatadorUtil.toLowerCase(name), FormatadorUtil.toLowerCase(description), price);
    }
}
