package com.snrroko.airbnb.controllers;

import com.snrroko.airbnb.advices.APISuccess;
import com.snrroko.airbnb.advices.APISuccessList;
import com.snrroko.airbnb.advices.APISuccessPaginated;
import com.snrroko.airbnb.advices.SuccessResponseHandler;
import com.snrroko.airbnb.dto.AccommodationResponseDto;
import com.snrroko.airbnb.dto.AccommodationSearchDto;
import com.snrroko.airbnb.services.AccommodationSearchService;
import com.snrroko.airbnb.services.AccommodationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "accommodations")
@RequiredArgsConstructor
public class AccommodationController {

    private final AccommodationService accommodationService;
    private final AccommodationSearchService accommodationSearchService;

    @GetMapping(path = "search")
    private ResponseEntity<APISuccessPaginated<AccommodationResponseDto>> searchForAccommodation(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String accommodationType,
            @RequestParam(defaultValue = "0") Integer page
    ) {
        String message = "Search completed";
        Page<AccommodationResponseDto> accommodationSearchPageResults = accommodationSearchService.searchForAccommodations(
                new AccommodationSearchDto(city, name, accommodationType),
                page
        );
        return SuccessResponseHandler.paginatedData(
                HttpStatus.OK,
                message,
                accommodationSearchPageResults.getContent(),
                accommodationSearchPageResults.getNumberOfElements(),
                accommodationSearchPageResults.getNumber()
        );
    }

    @GetMapping("{accommodationId}")
    private ResponseEntity<APISuccess<AccommodationResponseDto>> getAccommodationById (@PathVariable("accommodationId") UUID accommodationId) {
        return SuccessResponseHandler.singleData(
                HttpStatus.OK,
                "Accommodation retrieved successfully",
                accommodationService.findAccommodationById(accommodationId)
        );
    }
}
