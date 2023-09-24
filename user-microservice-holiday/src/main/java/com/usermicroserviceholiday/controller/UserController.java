package com.usermicroserviceholiday.controller;

import com.usermicroserviceholiday.payload.BookingDto;
import com.usermicroserviceholiday.payload.UserBookingDetailsDto;
import com.usermicroserviceholiday.payload.UserDto;
import com.usermicroserviceholiday.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto createdUser = userService.createUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        UserDto updatedUser = userService.updateUser(id, userDto);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{userId}/bookings")
    public ResponseEntity<BookingDto> createBooking(
            @PathVariable Long userId,
            @RequestBody BookingDto bookingDto
    ) {
        try {
            BookingDto createdBooking = userService.createBooking(userId, bookingDto);
            return new ResponseEntity<>(createdBooking, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{userId}/bookings")
    public ResponseEntity<List<BookingDto>> getUserBookings(@PathVariable Long userId) {
        List<BookingDto> bookings = userService.getUserBookings(userId);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }
    @GetMapping("/{userId}/bookingDetails")
    public ResponseEntity<List<UserBookingDetailsDto>> getUserBookingDetails(@PathVariable Long userId) {
        List<UserBookingDetailsDto> userBookingDetails = userService.getUserBookingDetails(userId);
        return new ResponseEntity<>(userBookingDetails, HttpStatus.OK);
    }


    // ... Other endpoints and methods ...

}