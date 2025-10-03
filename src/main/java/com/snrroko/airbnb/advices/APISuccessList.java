package com.snrroko.airbnb.advices;

import lombok.Data;

import java.util.List;

@Data
public class APISuccessList<T> {
    private String message;
    private List<T> data;
}
