package com.example.csci318.hotelbooking.repository;

import com.example.csci318.hotelbooking.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
