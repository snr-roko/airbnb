package com.snrroko.airbnb.dto;

import com.snrroko.airbnb.entities.enums.AccommodationStatus;
import com.snrroko.airbnb.entities.enums.AccommodationType;
import com.snrroko.airbnb.entities.enums.RoomSize;

import lombok.Data;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class AccommodationResponseDto {

    private UUID id;

    private String description;

    private String additionalInformation;

    private String type;

    private Integer capacity;

    private Integer quantity;

    private BigDecimal pricePerNight;

    private BigDecimal pricePerHour;

    private BigDecimal pricePerWeek;

    private BigDecimal pricePerMonth;

    private Double sizeInSqMeter;

    private Integer minimumStayInDays;

    private Boolean hasAC;

    private String status;

    private List<String> amenities;

    private List<String> images;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
