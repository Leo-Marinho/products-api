package com.compasso.productsms.utils;

import java.util.Objects;

public class FormatadorUtil {

    public static String toLowerCase(final String valor) {
        return (Objects.nonNull(valor)) ? valor.toLowerCase() : valor;
    }
}
