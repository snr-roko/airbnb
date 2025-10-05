package com.snrroko.airbnb.dto.roomType;

import com.snrroko.airbnb.entities.enums.RoomSize;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RoomTypeDto {

    private String name;

    private RoomSize size;

    private BigDecimal basePrice;

    private BigDecimal surgeFactor;

    private String[] amenities;

}
