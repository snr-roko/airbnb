package com.snrroko.airbnb.dto.hotel;

import com.snrroko.airbnb.entities.HotelContactInfo;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class HotelResponseDto {
    private UUID id;
    private String name;
    private String city;
    private String[] photos;
    private String[] amenities;
    private Boolean active;
    private HotelContactInfo contactInfo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
