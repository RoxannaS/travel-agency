package com.adobe.travelagency.aop;

import com.adobe.travelagency.exception.NotFoundDestinationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundDestinationException.class)
    public ResponseEntity<String> handleNotFoundDestinationException(NotFoundDestinationException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}

