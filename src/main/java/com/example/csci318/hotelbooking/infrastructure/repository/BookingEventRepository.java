package com.example.csci318.hotelbooking.infrastructure.repository;

import com.example.csci318.hotelbooking.domain.model.event.BookingEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingEventRepository extends JpaRepository<BookingEvent, Long> {
}
