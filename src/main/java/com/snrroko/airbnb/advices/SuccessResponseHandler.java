package com.snrroko.airbnb.advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class SuccessResponseHandler {
    private SuccessResponseHandler () {
        throw new IllegalStateException("Utility class");
    }

    public static <T> ResponseEntity<APISuccessList<T>> multipleData(
            HttpStatus status,
            String message,
            List<T> data
            ) {
        APISuccessList<T> payload = new APISuccessList<T>();
        payload.setMessage(message);
        payload.setData(data);
        return new ResponseEntity<>(payload, status);
    }

    public static <T> ResponseEntity<APISuccess<T>> singleData(
            HttpStatus status,
            String message,
            T data
    ) {
        APISuccess<T> payload = new APISuccess<T>();
        payload.setMessage(message);
        payload.setData(data);
        return new ResponseEntity<>(payload, status);
    }
}
