package com.snrroko.airbnb.dto;

import com.snrroko.airbnb.entities.PropertyContactInfo;
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
    private PropertyContactInfo contactInfo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
