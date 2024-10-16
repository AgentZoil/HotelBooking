package com.example.csci318.hotelbooking.infrastructure.repository;

import com.example.csci318.hotelbooking.domain.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
