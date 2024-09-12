package com.example.csci318.hotelbooking.repository;

import com.example.csci318.hotelbooking.model.event.RoomEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomEventRepository extends JpaRepository<RoomEvent, Long> {
}
