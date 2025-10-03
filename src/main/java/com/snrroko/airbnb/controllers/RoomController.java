package com.snrroko.airbnb.controllers;

import com.snrroko.airbnb.advices.APISuccess;
import com.snrroko.airbnb.advices.APISuccessList;
import com.snrroko.airbnb.advices.SuccessResponseHandler;
import com.snrroko.airbnb.dto.room.RoomResponseDto;
import com.snrroko.airbnb.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "hotels")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping("{hotelId}/rooms")
    private ResponseEntity<APISuccessList<RoomResponseDto>> getAllRoomsInAHotel (@PathVariable UUID hotelId) {
        return SuccessResponseHandler.multipleData(
                HttpStatus.OK,
                "All rooms retrieved successfully",
                roomService.findAllRoomsInAHotel(hotelId)
        );
    }
}
