package com.usermicroserviceholiday.feignClients;

import com.usermicroserviceholiday.payload.HolidayPackageDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOLIDAY-PACKAGE-SERVICE",url = "http://localhost:8081") // Replace with actual package service host and port
public interface PackageFeignClient {

    @GetMapping("/api/holidayPackage/{id}")
    HolidayPackageDto getPackageById(@PathVariable Long id);
}