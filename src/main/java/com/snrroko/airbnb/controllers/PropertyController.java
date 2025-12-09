package com.snrroko.airbnb.controllers;

import com.snrroko.airbnb.advices.APISuccess;
import com.snrroko.airbnb.advices.APISuccessList;
import com.snrroko.airbnb.advices.SuccessResponseHandler;
import com.snrroko.airbnb.dto.AccommodationDto;
import com.snrroko.airbnb.dto.AccommodationResponseDto;
import com.snrroko.airbnb.dto.PropertyDto;
import com.snrroko.airbnb.dto.PropertyResponseDto;
import com.snrroko.airbnb.services.AccommodationService;
import com.snrroko.airbnb.services.PropertyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "properties")
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;
    private final AccommodationService accommodationService;

    @GetMapping
    public ResponseEntity<APISuccessList<PropertyResponseDto>> getAllProperties() {
        return SuccessResponseHandler.multipleData(
                HttpStatus.OK,
                "Properties retrieved successfully",
                propertyService.findAllProperties()
                );
    }

   @GetMapping("{id}")
   public ResponseEntity<APISuccess<PropertyResponseDto>> getPropertyById(@PathVariable UUID id) {
        return SuccessResponseHandler.singleData(
                HttpStatus.OK,
                "Property retrieved successfully",
                propertyService.findPropertyById(id)
        );
   }

   @PostMapping
    public ResponseEntity<APISuccess<PropertyResponseDto>> addProperty (@RequestBody @Valid PropertyDto propertyDto) {
        return SuccessResponseHandler.singleData(
                HttpStatus.CREATED,
                "Property created successfully",
                propertyService.createNewProperty(propertyDto)
        );
    }

    @PutMapping("{id}")
    public ResponseEntity<APISuccess<PropertyResponseDto>> FullUpdateProperty(@PathVariable UUID id, @RequestBody @Valid PropertyDto propertyDto) {
        return SuccessResponseHandler.singleData(
                HttpStatus.OK,
                "Property updated successfully",
                propertyService.fullUpdatePropertyById(id, propertyDto)
        );
    }

    @PatchMapping("{id}")
    public ResponseEntity<APISuccess<PropertyResponseDto>> PatchUpdateProperty(@PathVariable UUID id, @RequestBody @Valid PropertyDto propertyDto) {
        return SuccessResponseHandler.singleData(
                HttpStatus.OK,
                "Property updated successfully",
                propertyService.partialUpdatePropertyById(id, propertyDto)
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<APISuccessList<Object>> deletePropertyById(@PathVariable UUID id)  {
        propertyService.deletePropertyById(id);
        return SuccessResponseHandler.multipleData(
                HttpStatus.OK,
                "Property deleted successfully",
                List.of()
        );
    }

    @PatchMapping("{id}/activate")
    public ResponseEntity<APISuccess<PropertyResponseDto>> activatePropertyById(@PathVariable UUID id) {
        return SuccessResponseHandler.singleData(
                HttpStatus.OK,
                "Property activated successfully",
                propertyService.activatePropertyById(id)
        );
    }

    @PatchMapping("{id}/deactivate")
    public ResponseEntity<APISuccess<PropertyResponseDto>> deactivatePropertyById(@PathVariable UUID id) {
        return SuccessResponseHandler.singleData(
                HttpStatus.OK,
                "Property deactivated successfully",
                propertyService.deactivatePropertyById(id)
        );
    }

    @GetMapping("{propertyId}/accommodations")
    private ResponseEntity<APISuccessList<AccommodationResponseDto>> getAllAccommodationsInAProperty (@PathVariable UUID propertyId) {
        return SuccessResponseHandler.multipleData(
                HttpStatus.OK,
                "All accommodations retrieved successfully",
                accommodationService.findAllAccommodationsInAProperty(propertyId)
        );
    }

    @PostMapping("{propertyId}/accommodations")
    private ResponseEntity<APISuccess<AccommodationResponseDto>> createAccommodation (@PathVariable("propertyId") UUID propertyId, @RequestBody @Valid AccommodationDto newAccommodation) {
        return SuccessResponseHandler.singleData(
                HttpStatus.CREATED,
                "Accommodation added successfully",
                accommodationService.addAccommodationToProperty(propertyId, newAccommodation)
        );
    }
}
