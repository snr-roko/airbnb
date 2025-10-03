package com.snrroko.airbnb.dto.room;

import com.snrroko.airbnb.entities.Hotel;

import java.math.BigDecimal;

public class RoomDto {

    private Hotel hotel;

    private String type;

    private BigDecimal basePrice;

    private String[] photos;

    private String[] amenities;

    private Integer totalCount;

    private Integer capacity;
}
