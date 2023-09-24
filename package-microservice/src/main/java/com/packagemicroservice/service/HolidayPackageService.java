package com.packagemicroservice.service;

import com.packagemicroservice.payload.HolidayPackageDto;


import java.util.List;

public interface HolidayPackageService {
    List<HolidayPackageDto> getAllPackages();
    HolidayPackageDto getPackageById(Long id);
    HolidayPackageDto createPackage(HolidayPackageDto packageDto);
    HolidayPackageDto updatePackage(Long id, HolidayPackageDto packageDto);
    void deletePackage(Long id);
}