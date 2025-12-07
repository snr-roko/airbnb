package com.snrroko.airbnb.dto;

import com.snrroko.airbnb.entities.PropertyContactInfo;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PropertyDto {
    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "city is required")
    private String city;

    private String[] photos;

    private String[] amenities;

    private PropertyContactInfo contactInfo;
}
