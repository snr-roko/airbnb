package com.snrroko.airbnb.services;

import com.snrroko.airbnb.dto.room.RoomResponseDto;
import com.snrroko.airbnb.entities.Hotel;
import com.snrroko.airbnb.entities.Room;
import com.snrroko.airbnb.exceptions.ResourceNotFoundException;
import com.snrroko.airbnb.repositories.HotelRepository;
import com.snrroko.airbnb.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@Slf4j
@RequiredArgsConstructor
public class RoomService {

    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;

    public List<RoomResponseDto> findAllRoomsInAHotel (UUID hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel Not Found"));
        return hotel
                .getRooms()
                .stream()
                .map((room) -> modelMapper.map(room, RoomResponseDto.class))
                .toList();
    }


}
