package com.snrroko.airbnb.advices;

import com.snrroko.airbnb.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ResourceNotFoundException.class)
    private ResponseEntity<APIError> handleNotFoundException(ResourceNotFoundException exception) {
        APIError body = new APIError();
        body.setMessage(exception.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

}
