package com.compasso.productsms.model.product;

import com.compasso.productsms.dto.ProductDTO;
import com.compasso.productsms.utils.MessageValidation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "serial")
    private Long idProduct;

    @Size(max = 100, message = MessageValidation.SIZE_NAME)
    @NotBlank(message = MessageValidation.NAME_NOT_BLANK)
    private String name;

    @Size(max = 200, message = MessageValidation.SIZE_DESCRIPTION)
    @NotBlank(message = MessageValidation.DESCRIPTION_NOT_BLANK)
    private String description;

    @PositiveOrZero(message = MessageValidation.NUMBER_NEGATIVE)
    private BigDecimal price;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date dateCreated;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date dateUpdated;

    public ProductEntity(final String name, final String description, final BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public ProductDTO toDTO() {
        return new ProductDTO(idProduct, name, description, price);
    }
}
