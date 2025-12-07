package com.snrroko.airbnb.advices;

import lombok.Data;

import java.util.List;

@Data
public class PageData<T> {
    private Integer count;
    private Integer currentPage;
    private List<T> content;
}
