package com.snrroko.airbnb.services;

import com.snrroko.airbnb.dto.hotel.HotelDto;
import com.snrroko.airbnb.dto.hotel.HotelResponseDto;
import com.snrroko.airbnb.entities.Hotel;
import com.snrroko.airbnb.repositories.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;

    public HotelResponseDto createNewHotel(HotelDto hotelDto) {
        Hotel hotel = modelMapper.map(hotelDto, Hotel.class);
        hotel.setActive(false);
        Hotel newHotel = hotelRepository.save(hotel);
        return modelMapper.map(newHotel, HotelResponseDto.class);
    }

    public HotelResponseDto findHotelById(UUID id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow( () -> new RuntimeException("Hotel not found"));
        return modelMapper.map(hotel, HotelResponseDto.class);
    }

    public List<HotelResponseDto> findAllHotels() {
        List<Hotel> allHotels = hotelRepository.findAll();
        return allHotels
                .stream()
                .map((hotel) -> modelMapper.map(hotel, HotelResponseDto.class))
                .toList();
    }
}
