package com.packagemicroservice.controller;

import com.packagemicroservice.payload.HolidayPackageDto;
import com.packagemicroservice.service.HolidayPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/holidayPackage")
public class HolidayPackageController {

    @Autowired
    private HolidayPackageService packageService;

    @GetMapping
    public List<HolidayPackageDto> getAllPackages() {
        return packageService.getAllPackages();
    }

    @GetMapping("/{id}")
    public HolidayPackageDto getPackageById(@PathVariable Long id) {
        return packageService.getPackageById(id);
    }

    @PostMapping
    public HolidayPackageDto createPackage(@RequestBody HolidayPackageDto packageDto) {
        return packageService.createPackage(packageDto);
    }

    @PutMapping("/{id}")
    public HolidayPackageDto updatePackage(@PathVariable Long id, @RequestBody HolidayPackageDto packageDto) {
        return packageService.updatePackage(id, packageDto);
    }

    @DeleteMapping("/{id}")
    public void deletePackage(@PathVariable Long id) {
        packageService.deletePackage(id);
    }
}

