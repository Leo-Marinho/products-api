package com.compasso.productsms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductIdNotFount extends RuntimeException {

    public ProductIdNotFount(final String message) {
        super(message);
    }
}
