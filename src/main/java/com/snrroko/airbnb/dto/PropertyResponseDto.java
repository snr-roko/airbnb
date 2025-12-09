package com.snrroko.airbnb.dto;

import com.snrroko.airbnb.entities.PropertyContactInfo;
import com.snrroko.airbnb.entities.enums.BookingMode;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PropertyResponseDto {
    private UUID id;
    private String name;
    private String city;
    private String[] photos;
    private String[] amenities;
    private Boolean active;
    private String bookingMode;
    private PropertyContactInfo contactInfo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
