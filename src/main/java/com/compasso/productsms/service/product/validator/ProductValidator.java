package com.compasso.productsms.service.product.validator;

import com.compasso.productsms.annotations.Validator;
import com.compasso.productsms.dto.ProductDTO;
import com.compasso.productsms.exception.PriceProductNegativeException;
import com.compasso.productsms.utils.ExceptionMessage;
import com.compasso.productsms.validator.ValidatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;

import java.math.BigDecimal;

@Slf4j
@Validator
@Qualifier("productValidator")
public class ProductValidator implements ValidatorService<ProductDTO> {

    private Boolean isValid;

    @Override
    public void validate(final ProductDTO productDTO) {
        if(priceProductIsNegative(productDTO)) {
            log.error("ProductValidator - Valor do produto inválido.");
            throw new PriceProductNegativeException(ExceptionMessage.INVALID_PRICE_PRODUCT);
        }
    }

    private boolean priceProductIsNegative(final ProductDTO productDTO) {
        return productDTO.getPrice().compareTo(BigDecimal.ZERO) < 0;
    }
}
