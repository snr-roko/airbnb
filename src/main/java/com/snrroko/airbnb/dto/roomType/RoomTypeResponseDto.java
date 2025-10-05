package com.snrroko.airbnb.dto.roomType;

import com.snrroko.airbnb.entities.enums.RoomSize;

import lombok.Data;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class RoomTypeResponseDto {

    private Long id;

    private BigDecimal basePrice;

    private BigDecimal surgeFactor;

    private String[] amenities;

    private Integer totalRoomCount;

    private String name;

    private RoomSize size;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
