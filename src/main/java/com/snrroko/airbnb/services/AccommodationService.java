package com.snrroko.airbnb.services;

import com.snrroko.airbnb.dto.AccommodationDto;
import com.snrroko.airbnb.dto.AccommodationResponseDto;
import com.snrroko.airbnb.entities.Property;
import com.snrroko.airbnb.entities.Accommodation;
import com.snrroko.airbnb.exceptions.ResourceNotFoundException;
import com.snrroko.airbnb.repositories.PropertyRepository;
import com.snrroko.airbnb.repositories.AccommodationRepository;
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
public class AccommodationService {

    private final PropertyRepository propertyRepository;
    private final AccommodationRepository accommodationRepository;
    private final ModelMapper modelMapper;

    public List<AccommodationResponseDto> findAllAccommodationsInAProperty (UUID propertyId) {
        Property property = propertyRepository.findById(propertyId).orElseThrow(() -> new ResourceNotFoundException("Property Not Found"));
        return property
                .getAccommodations()
                .stream()
                .map((accommodation) -> modelMapper.map(accommodation, AccommodationResponseDto.class))
                .toList();
    }

    @Transactional
    public AccommodationResponseDto addAccommodationToProperty (UUID propertyId, AccommodationDto newAccommodation) {
        Property property = propertyRepository.findById(propertyId).orElseThrow(() -> new ResourceNotFoundException("Property Not Found"));
        Accommodation accommodationToBeCreated = modelMapper.map(newAccommodation, Accommodation.class);
        accommodationToBeCreated.setProperty(property);
        Accommodation accommodationSaved = accommodationRepository.save(accommodationToBeCreated);
        return modelMapper.map(accommodationSaved, AccommodationResponseDto.class);
    }

    public AccommodationResponseDto findAccommodationById (UUID accommodationId) {
        Accommodation accommodation = accommodationRepository.findById(accommodationId).orElseThrow(() -> new ResourceNotFoundException("Accommodation Not Found"));
        return modelMapper.map(accommodation, AccommodationResponseDto.class);
    }

}
