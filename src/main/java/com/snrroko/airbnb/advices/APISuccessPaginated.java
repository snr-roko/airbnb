package com.snrroko.airbnb.advices;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class APISuccessPaginated<T> {
    private String message;
    private PageData<T> data;
}
