package com.snrroko.airbnb.controllers;

import com.snrroko.airbnb.advices.APISuccess;
import com.snrroko.airbnb.advices.APISuccessList;
import com.snrroko.airbnb.advices.SuccessResponseHandler;
import com.snrroko.airbnb.dto.hotel.HotelDto;
import com.snrroko.airbnb.dto.hotel.HotelResponseDto;
import com.snrroko.airbnb.services.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "admin/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @GetMapping
    public ResponseEntity<APISuccessList<HotelResponseDto>> getAllHotels() {
        return SuccessResponseHandler.multipleData(
                HttpStatus.OK,
                "Hotels retrieved successfully",
                hotelService.findAllHotels()
                );
    }

   @GetMapping("{id}")
   public ResponseEntity<APISuccess<HotelResponseDto>> getHotelById(@PathVariable UUID id) {
        return SuccessResponseHandler.singleData(
                HttpStatus.OK,
                "Hotel retrieved successfully",
                hotelService.findHotelById(id)
        );
   }

   @PostMapping
    public ResponseEntity<APISuccess<HotelResponseDto>> addHotel (@RequestBody HotelDto hotelDto) {
        return SuccessResponseHandler.singleData(
                HttpStatus.CREATED,
                "Hotel created successfully",
                hotelService.createNewHotel(hotelDto)
        );
    }

    @PutMapping("{id}")
    public ResponseEntity<APISuccess<HotelResponseDto>> FullUpdateHotel(@PathVariable UUID id, @RequestBody HotelDto hotelDto) {
        return SuccessResponseHandler.singleData(
                HttpStatus.OK,
                "Hotel updated successfully",
                hotelService.fullUpdateHotelById(id, hotelDto)
        );
    }

    @PatchMapping("{id}")
    public ResponseEntity<APISuccess<HotelResponseDto>> PatchUpdateHotel(@PathVariable UUID id, @RequestBody HotelDto hotelDto) {
        return SuccessResponseHandler.singleData(
                HttpStatus.OK,
                "Hotel updated successfully",
                hotelService.partialUpdateHotelById(id, hotelDto)
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<APISuccessList<Object>> deleteHotelById(@PathVariable UUID id)  {
        hotelService.deleteHotelById(id);
        return SuccessResponseHandler.multipleData(
                HttpStatus.OK,
                "Hotel deleted successfully",
                List.of()
        );
    }

    @PatchMapping("{id}/activate")
    public ResponseEntity<APISuccess<HotelResponseDto>> activateHotelById(@PathVariable UUID id) {
        return SuccessResponseHandler.singleData(
                HttpStatus.OK,
                "Hotel activated successfully",
                hotelService.activateHotelById(id)
        );
    }

    @PatchMapping("{id}/deactivate")
    public ResponseEntity<APISuccess<HotelResponseDto>> deactivateHotelById(@PathVariable UUID id) {
        return SuccessResponseHandler.singleData(
                HttpStatus.OK,
                "Hotel deactivated successfully",
                hotelService.deactivateHotelById(id)
        );
    }
}
