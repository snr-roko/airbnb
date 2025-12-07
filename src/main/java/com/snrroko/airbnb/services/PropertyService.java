package com.snrroko.airbnb.services;

import com.snrroko.airbnb.dto.PropertyDto;
import com.snrroko.airbnb.dto.PropertyResponseDto;
import com.snrroko.airbnb.entities.Property;
import com.snrroko.airbnb.exceptions.ResourceNotFoundException;
import com.snrroko.airbnb.repositories.PropertyRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final ModelMapper modelMapper;
    private final ModelMapper patchMapper;

    public PropertyService(PropertyRepository propertyRepository, ModelMapper modelMapper, @Qualifier("patchMapper")ModelMapper patchMapper) {
        this.propertyRepository = propertyRepository;
        this.modelMapper = modelMapper;
        this.patchMapper = patchMapper;
    }

    public PropertyResponseDto createNewProperty(PropertyDto propertyDto) {
        Property property = modelMapper.map(propertyDto, Property.class);
        property.setActive(false);
        Property newProperty = propertyRepository.save(property);
        return modelMapper.map(newProperty, PropertyResponseDto.class);
    }

    public PropertyResponseDto findPropertyById(UUID id) {
        Property property = propertyRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Property not found"));
        return modelMapper.map(property, PropertyResponseDto.class);
    }

    public List<PropertyResponseDto> findAllProperties() {
        List<Property> allProperties = propertyRepository.findAll();
        return allProperties
                .stream()
                .map((hotel) -> modelMapper.map(hotel, PropertyResponseDto.class))
                .toList();
    }

    @Transactional
    public PropertyResponseDto fullUpdatePropertyById(UUID id, PropertyDto propertyDto) {
        Property property = propertyRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Property not found"));
        Boolean active = property.getActive();
        modelMapper.map(propertyDto, property);
        property.setId(id);
        property.setActive(active);
        property.setAmenities(propertyDto.getAmenities());
        property.setPhotos(propertyDto.getPhotos());
        return modelMapper.map(property, PropertyResponseDto.class);
    }

    @Transactional
    public PropertyResponseDto partialUpdatePropertyById(UUID id, PropertyDto propertyDto) {
        Property property = propertyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Property not found"));
        patchMapper.map(propertyDto, property);
        if (propertyDto.getPhotos() != null) {
            property.setPhotos(propertyDto.getPhotos());
        }
        if (propertyDto.getAmenities() != null) {
            property.setAmenities(propertyDto.getAmenities());
        }
        return modelMapper.map(property, PropertyResponseDto.class);
    }

    @Transactional
    public void deletePropertyById(UUID id) {
        boolean exists = propertyRepository.existsById(id);
        if (!exists) {
            throw new ResourceNotFoundException("Property not found");
        }
        propertyRepository.deleteById(id);
    }

    @Transactional
    public PropertyResponseDto activatePropertyById(UUID id) {
        Property property = propertyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Property not found"));
        property.setActive(true);
        return modelMapper.map(property, PropertyResponseDto.class);
    }

    @Transactional
    public PropertyResponseDto deactivatePropertyById(UUID id) {
        Property property = propertyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Property not found"));
        property.setActive(false);
        return modelMapper.map(property, PropertyResponseDto.class);
    }

}
