package com.snrroko.airbnb.controllers;

import com.snrroko.airbnb.advices.APISuccess;
import com.snrroko.airbnb.advices.APISuccessList;
import com.snrroko.airbnb.advices.SuccessResponseHandler;
import com.snrroko.airbnb.dto.roomType.RoomTypeDto;
import com.snrroko.airbnb.dto.roomType.RoomTypeResponseDto;
import com.snrroko.airbnb.services.RoomTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class RoomTypeController {

    private final RoomTypeService roomTypeService;

    @GetMapping("hotels/{hotelId}/room_types")
    private ResponseEntity<APISuccessList<RoomTypeResponseDto>> getAllRoomTypesInAHotel (@PathVariable UUID hotelId) {
        return SuccessResponseHandler.multipleData(
                HttpStatus.OK,
                "All room types retrieved successfully",
                roomTypeService.findAllRoomTypesInAHotel(hotelId)
        );
    }

    @PostMapping("hotels/{hotelId}/room_types")
    private ResponseEntity<APISuccess<RoomTypeResponseDto>> createRoomType (@PathVariable UUID hotelId, @RequestBody RoomTypeDto newRoomType) {
        return SuccessResponseHandler.singleData(
                HttpStatus.CREATED,
                "Room type added successfully",
                roomTypeService.addRoomTypeToHotel(hotelId, newRoomType)
        );
    }

    @GetMapping("room_types/{roomTypeId}")
    private ResponseEntity<APISuccess<RoomTypeResponseDto>> getRoomTypeById (@PathVariable Long roomTypeId) {
        return SuccessResponseHandler.singleData(
                HttpStatus.OK,
                "Room retrieved successfully",
                roomTypeService.findOneRoomTypeInAHotelById(roomTypeId)
        );
    }
}
