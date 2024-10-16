package com.example.csci318.hotelbooking.infrastructure.repository;

import com.example.csci318.hotelbooking.domain.event.HotelEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelEventRepository extends JpaRepository<HotelEvent, Long> {
}
