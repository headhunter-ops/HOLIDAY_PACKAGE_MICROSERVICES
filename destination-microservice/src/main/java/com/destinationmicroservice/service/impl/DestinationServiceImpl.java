package com.destinationmicroservice.service.impl;

import com.destinationmicroservice.entity.Destination;
import com.destinationmicroservice.exception.ResourceNotFoundException;
import com.destinationmicroservice.payload.DestinationDto;
import com.destinationmicroservice.repository.DestinationRepository;
import com.destinationmicroservice.service.DestinationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class DestinationServiceImpl implements DestinationService {

    @Autowired
    private DestinationRepository destinationRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<DestinationDto> getAllDestinations() {
        List<Destination> all = destinationRepository.findAll();
        return all.stream().map(this::mapToDto).collect(Collectors.toList());

    }

    @Override
    public DestinationDto getDestinationById(Long id) {
        Destination destination = destinationRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Destination", "id", id)
        );
                return mapToDto(destination);
    }

    @Override
    public DestinationDto createDestination(DestinationDto destinationDto) {
        Destination destination = mapToEntity(destinationDto);
        Destination save = destinationRepository.save(destination);
        DestinationDto destinationDto1 = mapToDto(save);
        return destinationDto1;

    }

    @Override
    public DestinationDto updateDestination(Long id, DestinationDto destinationDto) {
        Destination destination = destinationRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Destination", "id", id)
        );
        destination.setName(destinationDto.getName());
        destination.setDescription(destinationDto.getDescription());
        destination.setActivities(destinationDto.getActivities());
        destinationRepository.save(destination);
        return mapToDto(destination);

    }

    @Override
    public void deleteDestination(Long id) {
         destinationRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Destination", "id", id)
        );
        destinationRepository.deleteById(id);

    }

    //--------------------//----------conversion of entity to dto and vice versa---------/--------------//------------//
    public Destination mapToEntity(DestinationDto destinationDto){
        return modelMapper.map(destinationDto,Destination.class);
    }

    public DestinationDto mapToDto(Destination destination){
        return modelMapper.map(destination,DestinationDto.class);
    }
}
