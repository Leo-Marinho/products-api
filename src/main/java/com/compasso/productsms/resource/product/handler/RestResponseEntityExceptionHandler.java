package com.compasso.productsms.resource.product.handler;

import com.compasso.productsms.exception.NotFoundException;
import com.compasso.productsms.exception.PriceProductNegativeException;
import com.compasso.productsms.exception.ProductIdNotFount;
import com.compasso.productsms.utils.MessageValidation;
import com.compasso.productsms.utils.StatusCode;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
@RestController
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<ExceptionResponse> handleStatusNullInvalidExcepiton(final ConstraintViolationException exception) {
        return new ResponseEntity<>(new ExceptionResponse(StatusCode.BAD_REQUEST.getStatusCode(),
                                    getMessageConstraintViolation(exception)), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PriceProductNegativeException.class)
    public final ResponseEntity<ExceptionResponse> handlePriceProductNegativeException(final PriceProductNegativeException exception) {
        return new ResponseEntity<>(createResponse(StatusCode.BAD_REQUEST.getStatusCode(), exception), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleNotFoundException(final NotFoundException exception) {
        return new ResponseEntity<>(createResponse(StatusCode.NOT_FOUND.getStatusCode(), exception), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductIdNotFount.class)
    public final ResponseEntity<HttpStatus> handleNotFoundException() {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public final ResponseEntity<HttpStatus> handleEmptyResultDataAccessException() {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private ExceptionResponse createResponse(final int statusCode, final Throwable ex) {
        return new ExceptionResponse(statusCode, ex.getMessage());
    }

    private String getMessageConstraintViolation(final ConstraintViolationException exception) {
        return exception.getConstraintViolations()
                        .stream()
                        .findAny()
                        .map(ConstraintViolation::getMessageTemplate)
                        .orElse(MessageValidation.UNDEFINED_MESSAGE);
    }
}

