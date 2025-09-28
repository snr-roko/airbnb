package com.snrroko.airbnb.dto.hotel;

import com.snrroko.airbnb.entities.HotelContactInfo;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class HotelDto {
    private String name;
    private String city;
    private String[] photos;
    private String[] amenities;
    private HotelContactInfo contactInfo;
}
