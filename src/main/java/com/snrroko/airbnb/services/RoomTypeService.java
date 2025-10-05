package com.snrroko.airbnb.services;

import com.snrroko.airbnb.dto.roomType.RoomTypeDto;
import com.snrroko.airbnb.dto.roomType.RoomTypeResponseDto;
import com.snrroko.airbnb.entities.Hotel;
import com.snrroko.airbnb.entities.RoomType;
import com.snrroko.airbnb.exceptions.ResourceNotFoundException;
import com.snrroko.airbnb.repositories.HotelRepository;
import com.snrroko.airbnb.repositories.RoomTypeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@Slf4j
@RequiredArgsConstructor
public class RoomTypeService {

    private final HotelRepository hotelRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final ModelMapper modelMapper;

    public List<RoomTypeResponseDto> findAllRoomTypesInAHotel (UUID hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel Not Found"));
        return hotel
                .getRoomTypes()
                .stream()
                .map((roomType) -> {
                    RoomTypeResponseDto response = modelMapper.map(roomType, RoomTypeResponseDto.class);
                    response.setTotalRoomCount(roomType.getRooms().size());
                    return response;
                })
                .toList();
    }

    @Transactional
    public RoomTypeResponseDto addRoomTypeToHotel (UUID hotelId, RoomTypeDto newRoomType) {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel Not Found"));
        RoomType roomTypeToBeCreated = modelMapper.map(newRoomType, RoomType.class);
        roomTypeToBeCreated.setHotel(hotel);
        RoomType roomTypeSaved = roomTypeRepository.save(roomTypeToBeCreated);
        RoomTypeResponseDto response = modelMapper.map(roomTypeSaved, RoomTypeResponseDto.class);
        response.setTotalRoomCount(roomTypeSaved.getRooms().size());
        return response;
    }

    public RoomTypeResponseDto findOneRoomTypeInAHotelById (Long roomTypId) {
        RoomType roomType = roomTypeRepository.findById(roomTypId).orElseThrow(() -> new ResourceNotFoundException("Room Type Not Found"));
        RoomTypeResponseDto response = modelMapper.map(roomType, RoomTypeResponseDto.class);
        response.setTotalRoomCount(roomType.getRooms().size());
        return response;
    }



}
