package com.snrroko.airbnb.services;

import com.snrroko.airbnb.dto.AccommodationResponseDto;
import com.snrroko.airbnb.dto.AccommodationSearchDto;
import com.snrroko.airbnb.entities.Accommodation;
import com.snrroko.airbnb.repositories.AccommodationRepository;
import com.snrroko.airbnb.repositories.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccommodationSearchService {

    private final PropertyRepository propertyRepository;
    private final AccommodationRepository accommodationRepository;
    private final ModelMapper modelMapper;

    private final int PAGE_SIZE = 10;

    public Page<AccommodationResponseDto> searchForAccommodations(AccommodationSearchDto accommodationSearchDetails, Integer page) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        Page<Accommodation> accommodationPage = accommodationRepository
                .searchForAccommodations(
                        accommodationSearchDetails.getCity(),
                        accommodationSearchDetails.getName(),
                        String.valueOf(accommodationSearchDetails.getAccommodationType()),
                        pageable
                        );

        return accommodationPage
                .map(accommodation -> modelMapper.map(accommodation, AccommodationResponseDto.class));
    }
}
