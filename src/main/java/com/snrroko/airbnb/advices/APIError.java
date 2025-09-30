package com.snrroko.airbnb.advices;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class APIError {
    private String message;
    private List<String> validationErrors = new ArrayList<>();
}
