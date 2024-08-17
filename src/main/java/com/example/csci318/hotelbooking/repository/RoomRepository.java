package com.example.csci318.hotelbooking.repository;

import com.example.csci318.hotelbooking.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
