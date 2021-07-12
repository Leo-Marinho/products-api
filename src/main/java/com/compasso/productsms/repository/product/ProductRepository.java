package com.compasso.productsms.repository.product;

import com.compasso.productsms.model.product.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {


    @Query("SELECT p FROM ProductEntity p WHERE (:minimumPrice IS NULL OR p.price >= :minimumPrice)" +
            "AND (:maximumPrice IS NULL OR p.price <= :maximumPrice)" +
            "AND (:criteriaFilter IS NULL OR p.name LIKE %:criteriaFilter% OR p.description LIKE %:criteriaFilter%)" )
    Page<ProductEntity> findByParamsFilter(@Param("criteriaFilter") final String criteriaFilter,
                                           @Param("minimumPrice") final BigDecimal minimumPrice,
                                           @Param("maximumPrice") final BigDecimal maximumPrice, final Pageable pageable);

}
