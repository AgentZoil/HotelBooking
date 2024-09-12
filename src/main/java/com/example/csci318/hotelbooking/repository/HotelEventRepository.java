package com.example.csci318.hotelbooking.repository;

import com.example.csci318.hotelbooking.model.event.HotelEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelEventRepository extends JpaRepository<HotelEvent, Long> {
}
