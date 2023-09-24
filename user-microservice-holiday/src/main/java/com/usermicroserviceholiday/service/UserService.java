package com.usermicroserviceholiday.service;

import com.usermicroserviceholiday.payload.BookingDto;
import com.usermicroserviceholiday.payload.UserBookingDetailsDto;
import com.usermicroserviceholiday.payload.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();
    UserDto getUserById(Long id);
    UserDto createUser(UserDto userDto);
    UserDto updateUser(Long id, UserDto userDto);
    void deleteUser(Long id);

    BookingDto createBooking(Long userId,BookingDto bookingDto);


    List<BookingDto> getUserBookings(Long userId);


    List<UserBookingDetailsDto> getUserBookingDetails(Long userId);
}