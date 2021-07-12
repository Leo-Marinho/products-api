package com.compasso.productsms.dto;

import com.compasso.productsms.model.product.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;

    public ProductDTO(final ProductEntity product) {
        this.id = product.getIdProduct();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
    }

    public ProductDTO(final String name, final String description, final BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public ProductEntity toEntity() {
        return new ProductEntity(name, description, price);
    }
}
