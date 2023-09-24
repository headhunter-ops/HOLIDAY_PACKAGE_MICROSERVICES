package com.destinationmicroservice.controller;

import com.destinationmicroservice.payload.DestinationDto;
import com.destinationmicroservice.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/destinations")
public class DestinationController {

    @Autowired
    private DestinationService destinationService;

    @GetMapping
    public List<DestinationDto> getAllDestinations() {
        return destinationService.getAllDestinations();
    }

    @GetMapping("/{id}")
    public DestinationDto getDestinationById(@PathVariable Long id) {
        return destinationService.getDestinationById(id);
    }

    @PostMapping
    public DestinationDto createDestination(@RequestBody DestinationDto destinationDto) {
        return destinationService.createDestination(destinationDto);
    }

    @PutMapping("/{id}")
    public DestinationDto updateDestination(@PathVariable Long id, @RequestBody DestinationDto destinationDto) {
        return destinationService.updateDestination(id, destinationDto);
    }

    @DeleteMapping("/{id}")
    public void deleteDestination(@PathVariable Long id) {
        destinationService.deleteDestination(id);
    }
}