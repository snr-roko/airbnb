package com.snrroko.airbnb.controllers;

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
    public ResponseEntity<List<HotelResponseDto>> getAllHotels() {
        return ResponseEntity.ok(hotelService.findAllHotels());
    }

   @GetMapping("{id}")
   public ResponseEntity<HotelResponseDto> getHotelById(@PathVariable UUID id) {
        return ResponseEntity.ok(hotelService.findHotelById(id));
   }

   @PostMapping
    public ResponseEntity<HotelResponseDto> addHotel (@RequestBody HotelDto hotelDto) {
        return new ResponseEntity<>(hotelService.createNewHotel(hotelDto), HttpStatus.CREATED);
    }
}
