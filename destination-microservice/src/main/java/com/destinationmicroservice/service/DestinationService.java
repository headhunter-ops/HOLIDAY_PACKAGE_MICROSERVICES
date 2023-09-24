package com.destinationmicroservice.service;

import com.destinationmicroservice.payload.DestinationDto;

import java.util.List;

public interface DestinationService {

        List<DestinationDto> getAllDestinations();
        DestinationDto getDestinationById(Long id);
        DestinationDto createDestination(DestinationDto destinationDto);
        DestinationDto updateDestination(Long id, DestinationDto destinationDto);
        void deleteDestination(Long id);

}
