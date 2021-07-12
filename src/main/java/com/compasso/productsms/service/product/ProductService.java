package com.compasso.productsms.service.product;

import com.compasso.productsms.dto.ProductDTO;
import com.compasso.productsms.resource.product.filter.ProductFilterDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Page<ProductDTO> searchAll(final Pageable pageable);

    ProductDTO searchById(final Long id);

    ProductDTO create(final ProductDTO toDTO);

    void deleteById(final Long id);

    ProductDTO update(final Long identificador, final ProductDTO toDTO);

    Page<ProductDTO> searchByParamsFilter(final Pageable pageable,final ProductFilterDTO minimumPrice);
}
