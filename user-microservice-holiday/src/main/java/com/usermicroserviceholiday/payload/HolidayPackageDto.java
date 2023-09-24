package com.usermicroserviceholiday.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HolidayPackageDto {
    private Long id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private List<String> destinations;
    private double price;
}