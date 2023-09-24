package com.usermicroserviceholiday.service.impl;

import com.usermicroserviceholiday.entity.Booking;
import com.usermicroserviceholiday.entity.User;
import com.usermicroserviceholiday.exception.ResourceNotFoundException;
import com.usermicroserviceholiday.feignClients.DestinationFeignClient;
import com.usermicroserviceholiday.feignClients.PackageFeignClient;
import com.usermicroserviceholiday.payload.*;
import com.usermicroserviceholiday.repository.BookingRepository;
import com.usermicroserviceholiday.repository.UserRepository;
import com.usermicroserviceholiday.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PackageFeignClient packageFeignClient;
    @Autowired
    private DestinationFeignClient destinationFeignClient;

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }


    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id)
        );
        return mapToDto(user);

    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = mapToEntity(userDto);
        User save = userRepository.save(user);
        return mapToDto(save);
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id)
        );
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setLastName(userDto.getLastName());
       user.setFirstName(userDto.getFirstName());
        User save = userRepository.save(user);
       return mapToDto(save);
    }

    @Override
    public void deleteUser(Long id) {
       userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id)
        );
        userRepository.deleteById(id);
    }

    @Override
    public BookingDto createBooking(Long userId, BookingDto bookingDto) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        // Check if the provided package and destination exist
        HolidayPackageDto packageDto = packageFeignClient.getPackageById(bookingDto.getPackageId());
        DestinationDto destinationDto = destinationFeignClient.getDestinationById(bookingDto.getDestinationId());

        if (packageDto == null || destinationDto == null) {
            throw new IllegalArgumentException("Invalid package or destination");
        }

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setPackageId(bookingDto.getPackageId());
        booking.setDestinationId(bookingDto.getDestinationId());
        Booking save = bookingRepository.save(booking);
        return mapToDto(save);
    }
    @Override
    public List<BookingDto> getUserBookings(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );

        List<Booking> bookings = bookingRepository.findByUser(user);
        return bookings.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<UserBookingDetailsDto> getUserBookingDetails(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );

        List<Booking> bookings = bookingRepository.findByUser(user);

        return bookings.stream()
                .map(booking -> {
                    UserBookingDetailsDto dto = new UserBookingDetailsDto();
                    dto.setUser(mapToDto(user));
                    dto.setBooking(mapToDto(booking));
                    dto.setPackageDetails(packageFeignClient.getPackageById(booking.getPackageId()));
                    dto.setDestinationDetails(destinationFeignClient.getDestinationById(booking.getDestinationId()));
                    return dto;
                })
                .collect(Collectors.toList());
    }


    //------------//------conversion from dto to entity and vice versa-----------//
    public User mapToEntity(UserDto userDto){
        return modelMapper.map(userDto,User.class);
    }

    public UserDto mapToDto(User user){
        return modelMapper.map(user,UserDto.class);
    }

    public Booking mapToEntity(BookingDto bookingDto){
        return modelMapper.map(bookingDto,Booking.class);
    }
    public BookingDto mapToDto(Booking booking){
        return modelMapper.map(booking,BookingDto.class);
    }

    private UserBookingDetailsDto mapToUserBookingDetailsDto(Booking booking, User user) {
        UserBookingDetailsDto dto = new UserBookingDetailsDto();
        dto.setUser(mapToDto(user));
        dto.setBooking(mapToDto(booking));
        return dto;
    }
}
