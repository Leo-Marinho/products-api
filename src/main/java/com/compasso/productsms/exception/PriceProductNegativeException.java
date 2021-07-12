package com.compasso.productsms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PriceProductNegativeException extends RuntimeException {

    public PriceProductNegativeException(final String message) {
        super(message);
    }
}
