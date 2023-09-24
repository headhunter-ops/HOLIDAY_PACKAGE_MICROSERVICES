package com.packagemicroservice.service.impl;

import com.packagemicroservice.entity.HolidayPackage;
import com.packagemicroservice.exception.ResourceNotFoundException;
import com.packagemicroservice.payload.HolidayPackageDto;
import com.packagemicroservice.repository.HolidayPackageRepository;
import com.packagemicroservice.service.HolidayPackageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HolidayServiceImpl implements HolidayPackageService {

    @Autowired
    private HolidayPackageRepository holidayPackageRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<HolidayPackageDto> getAllPackages() {
        List<HolidayPackage> all = holidayPackageRepository.findAll();
        return all.stream()
                .map(this::mapToDto)  // Assuming mapToDto is a method in the same class
                .collect(Collectors.toList());
    }

    @Override
    public HolidayPackageDto getPackageById(Long id) {
        HolidayPackage holidayPackage = holidayPackageRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Package", "id", id)
        );
        HolidayPackageDto holidayPackageDto = mapToDto(holidayPackage);
        return holidayPackageDto;
    }

    @Override
    public HolidayPackageDto createPackage(HolidayPackageDto packageDto) {
        HolidayPackage holidayPackage = mapToEntity(packageDto);
        HolidayPackage save = holidayPackageRepository.save(holidayPackage);
        HolidayPackageDto holidayPackageDto = mapToDto(save);
        return holidayPackageDto;
    }

    @Override
    public HolidayPackageDto updatePackage(Long id, HolidayPackageDto packageDto) {
        HolidayPackage holidayPackage = holidayPackageRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Package", "id", id)
        );
        holidayPackage.setName(packageDto.getName());
        holidayPackage.setPrice(packageDto.getPrice());
        holidayPackage.setDescription(packageDto.getDescription());
        holidayPackage.setDestinations(packageDto.getDestinations());
        holidayPackage.setEndDate(packageDto.getEndDate());
        holidayPackage.setStartDate(packageDto.getStartDate());
        HolidayPackage save = holidayPackageRepository.save(holidayPackage);
        HolidayPackageDto holidayPackageDto = mapToDto(save);
        return holidayPackageDto;

    }

    @Override
    public void deletePackage(Long id) {
        HolidayPackage holidayPackage = holidayPackageRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Package", "id", id)
        );
        holidayPackageRepository.deleteById(id);

    }

    //----------//-------------conversion from entity to dto and vice versa-----------//---------------//

    public HolidayPackage mapToEntity(HolidayPackageDto holidayPackageDto){
        return modelMapper.map(holidayPackageDto,HolidayPackage.class);
    }

    public HolidayPackageDto mapToDto(HolidayPackage holidayPackage){
        return modelMapper.map(holidayPackage,HolidayPackageDto.class);
    }
}
