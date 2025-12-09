package com.snrroko.airbnb.dto;

import com.snrroko.airbnb.entities.Property;
import com.snrroko.airbnb.entities.enums.AccommodationType;
import com.snrroko.airbnb.entities.enums.RoomSize;
import jakarta.persistence.ElementCollection;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class AccommodationDto {

    @NotBlank(message = "description is required")
    private String description;

    @NotBlank(message = "additionalInformation is required")
    private String additionalInformation;

    @NotBlank(message = "accommodation type is required")
    @Pattern(
            regexp = "WHOLE_PROPERTY|ROOM|APARTMENT|STUDIO",
            message = "Accommodation Type must be either of WHOLE PROPERTY, ROOM, APARTMENT, STUDIO"
    )
    private String type;

    @NotNull(message = "capacity is required")
    private Integer capacity;

    @NotNull(message = "quantity is required")
    private Integer quantity;

    @NotNull(message = "price per night is required")
    private BigDecimal pricePerNight;

    private BigDecimal pricePerHour;

    private BigDecimal pricePerWeek;

    private BigDecimal pricePerMonth;

    private Double sizeInSqMeter;

    private Integer minimumStayInDays;

    @NotNull(message = "AC availability is required")
    private Boolean hasAC;

    private List<String> amenities;

    private List<String> images;
}
