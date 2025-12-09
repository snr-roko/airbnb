package com.snrroko.airbnb.dto;

import com.snrroko.airbnb.entities.enums.AccommodationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccommodationSearchDto {

    private String city;
    private String name;

    @Pattern(
            regexp = "WHOLE_PROPERTY|ROOM|APARTMENT|STUDIO",
            message = "Accommodation Type must be either of WHOLE PROPERTY, ROOM, APARTMENT, STUDIO"
    )
    private String accommodationType;

}
