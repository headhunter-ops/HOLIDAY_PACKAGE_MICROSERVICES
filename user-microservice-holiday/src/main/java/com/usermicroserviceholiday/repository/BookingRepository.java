package com.usermicroserviceholiday.repository;

import com.usermicroserviceholiday.entity.Booking;
import com.usermicroserviceholiday.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByUser(User user);
}
