package com.compasso.productsms.utils;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StatusCode {

    NOT_FOUND(404, " Not Found Exception"),
    BAD_REQUEST(400, "Bad Request Exception");

    private int statusCode;
    private String description;

    public int getStatusCode() {
        return statusCode;
    }

    public String getDescription() {
        return description;
    }
}
