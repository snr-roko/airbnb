package com.snrroko.airbnb.advices;

import com.snrroko.airbnb.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
@Slf4j
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ResourceNotFoundException.class)
    private ResponseEntity<APIError> handleNotFoundException(ResourceNotFoundException exception) {
        APIError body = new APIError();
        body.setMessage(exception.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentTypeMismatchException.class)
    private ResponseEntity<APIError> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        APIError body = new APIError();
        body.setMessage("Something went wrong: Incorrect request path parameter type");
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    private ResponseEntity<APIError> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        APIError body = new APIError();
        body.setMessage(exception.getMessage());
        return new ResponseEntity<>(body, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NoResourceFoundException.class)
    private ResponseEntity<APIError> handleServletNoResourceFoundException(NoResourceFoundException exception) {
        APIError body = new APIError();
        body.setMessage("Request path can not be found");
        return new ResponseEntity<>(body, HttpStatus.NOT_IMPLEMENTED);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    private ResponseEntity<APIError> handleException(Exception exception) {
        APIError body = new APIError();
        body.setMessage(exception.getMessage());
        log.error(exception.getClass().getName());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
