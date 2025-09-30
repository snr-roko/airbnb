package com.snrroko.airbnb.services;

import com.snrroko.airbnb.dto.hotel.HotelDto;
import com.snrroko.airbnb.dto.hotel.HotelResponseDto;
import com.snrroko.airbnb.entities.Hotel;
import com.snrroko.airbnb.exceptions.ResourceNotFoundException;
import com.snrroko.airbnb.repositories.HotelRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;
    private final ModelMapper patchMapper;

    public HotelService(HotelRepository hotelRepository, ModelMapper modelMapper, @Qualifier("patchMapper")ModelMapper patchMapper) {
        this.hotelRepository = hotelRepository;
        this.modelMapper = modelMapper;
        this.patchMapper = patchMapper;
    }

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

    @Transactional
    public HotelResponseDto fullUpdateHotelById(UUID id, HotelDto hotelDto) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow( () -> new RuntimeException("Hotel not found"));
        Boolean active = hotel.getActive();
        modelMapper.map(hotelDto, hotel);
        hotel.setId(id);
        hotel.setActive(active);
        hotel.setAmenities(hotelDto.getAmenities());
        hotel.setPhotos(hotelDto.getPhotos());
        return modelMapper.map(hotel, HotelResponseDto.class);
    }

    @Transactional
    public HotelResponseDto partialUpdateHotelById(UUID id, HotelDto hotelDto) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel not found"));
        patchMapper.map(hotelDto, hotel);
        if (hotelDto.getPhotos() != null) {
            hotel.setPhotos(hotelDto.getPhotos());
        }
        if (hotelDto.getAmenities() != null) {
            hotel.setAmenities(hotelDto.getAmenities());
        }
        return modelMapper.map(hotel, HotelResponseDto.class);
    }

    @Transactional
    public void deleteHotelById(UUID id) {
        boolean exists = hotelRepository.existsById(id);
        if (!exists) {
            throw new ResourceNotFoundException("Hotel not found");
        }
        hotelRepository.deleteById(id);
    }

    @Transactional
    public HotelResponseDto activateHotelById(UUID id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel not found"));
        hotel.setActive(true);
        return modelMapper.map(hotel, HotelResponseDto.class);
    }

    @Transactional
    public HotelResponseDto deactivateHotelById(UUID id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel not found"));
        hotel.setActive(false);
        return modelMapper.map(hotel, HotelResponseDto.class);
    }


}
