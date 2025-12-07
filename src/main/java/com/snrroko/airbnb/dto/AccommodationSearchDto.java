package com.snrroko.airbnb.dto;

import com.snrroko.airbnb.entities.enums.AccommodationType;
import lombok.Data;

@Data
public class AccommodationSearchDto {
    private String city;
    private String name;
    private AccommodationType accommodationType;
}
