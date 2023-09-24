package com.usermicroserviceholiday.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBookingDetailsDto {
    private UserDto user;
    private BookingDto booking;
    private HolidayPackageDto packageDetails;
    private DestinationDto destinationDetails;
}