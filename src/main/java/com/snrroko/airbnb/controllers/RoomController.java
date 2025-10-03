package com.snrroko.airbnb.controllers;

import com.snrroko.airbnb.advices.APISuccess;
import com.snrroko.airbnb.advices.APISuccessList;
import com.snrroko.airbnb.advices.SuccessResponseHandler;
import com.snrroko.airbnb.dto.room.RoomDto;
import com.snrroko.airbnb.dto.room.RoomResponseDto;
import com.snrroko.airbnb.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("{hotelId}/rooms")
    private ResponseEntity<APISuccess<RoomResponseDto>> createRoom (@PathVariable UUID hotelId, @RequestBody RoomDto newRoom) {
        return SuccessResponseHandler.singleData(
                HttpStatus.CREATED,
                "Room added successfully",
                roomService.addRoomToHotel(hotelId, newRoom)
        );
    }
}
