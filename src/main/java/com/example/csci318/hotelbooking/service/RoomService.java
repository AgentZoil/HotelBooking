package com.example.csci318.hotelbooking.service;

import com.example.csci318.hotelbooking.model.Room;
import com.example.csci318.hotelbooking.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    // Get all rooms
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    // Get a room by ID
    public Optional<Room> getRoomById(Long id) {
        return roomRepository.findById(id);
    }

    // Create a new room
    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    public RoomService() {
    }

    // Update an existing room
    public Optional<Room> updateRoom(Long id, Room roomDetails) {
        return roomRepository.findById(id).map(room -> {
            room.setRoomNumber(roomDetails.getRoomNumber());
            room.setType(roomDetails.getType());
            room.setPrice(roomDetails.getPrice());
            room.setAvailability(roomDetails.isAvailability());
            room.setHotel(roomDetails.getHotel());
            return roomRepository.save(room);
        });
    }

    // Delete a room
    public Optional<Void> deleteRoom(Long id) {
        return roomRepository.findById(id).map(room -> {
            roomRepository.delete(room);
            return null;
        });
    }

    public void bookRoom(long Id){
        Room room = roomRepository.findById(Id).orElseThrow(RuntimeException::new);
        room.isBooked();
        roomRepository.save(room);
    }
}
