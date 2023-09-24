package com.usermicroserviceholiday.feignClients;

import com.usermicroserviceholiday.payload.DestinationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DESTINATION-SERVICE",url = "http://localhost:8082") // Replace with actual destination service host and port
public interface DestinationFeignClient {

    @GetMapping("/api/destinations/{id}")
    DestinationDto getDestinationById(@PathVariable Long id);
}