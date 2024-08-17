package com.example.csci318.hotelbooking.repository;

import com.example.csci318.hotelbooking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
