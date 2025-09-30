package com.snrroko.airbnb.advices;

import lombok.Data;

@Data
public class APISuccess {
    private String message;
    private Object data;
}
