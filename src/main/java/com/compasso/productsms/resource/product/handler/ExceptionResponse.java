package com.compasso.productsms.resource.product.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExceptionResponse {

    private int statusCode;
    private String message;
}
