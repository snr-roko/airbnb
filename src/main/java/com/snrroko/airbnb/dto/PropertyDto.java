package com.snrroko.airbnb.dto;

import com.snrroko.airbnb.entities.PropertyContactInfo;
import com.snrroko.airbnb.entities.enums.BookingMode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class PropertyDto {
    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "city is required")
    private String city;

    private String[] photos;

    private String[] amenities;

    @NotBlank(message = "booking mode is required")
    @Pattern(regexp = "WHOLE_PROPERTY|UNIT_ONLY|FLEXIBLE",
    message = "booking mode must be one of WHOLE_PROPERTY, UNIT_ONLY, FLEXIBLE"
    )
    private String bookingMode;

    private PropertyContactInfo contactInfo;
}
