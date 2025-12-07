package com.snrroko.airbnb.controllers;

import com.snrroko.airbnb.advices.APISuccess;
import com.snrroko.airbnb.advices.SuccessResponseHandler;
import com.snrroko.airbnb.dto.AccommodationResponseDto;
import com.snrroko.airbnb.services.AccommodationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "accommodations")
@RequiredArgsConstructor
public class AccommodationController {

    private final AccommodationService accommodationService;

    @GetMapping("{accommodationId}")
    private ResponseEntity<APISuccess<AccommodationResponseDto>> getAccommodationById (@PathVariable("accommodationId") UUID accommodationId) {
        return SuccessResponseHandler.singleData(
                HttpStatus.OK,
                "Accommodation retrieved successfully",
                accommodationService.findAccommodationById(accommodationId)
        );
    }
}
