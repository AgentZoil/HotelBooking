package com.example.csci318.hotelbooking.domain.service;

import com.example.csci318.hotelbooking.domain.model.Hotel;
import com.example.csci318.hotelbooking.domain.model.Room;
import com.example.csci318.hotelbooking.infrastructure.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private HotelService hotelService;
    private final RestTemplate restTemplate;

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
        // if the hotel is not specified
        Hotel hotel = hotelService.getHotelById(room.getHotel().getId())
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        room.locatedAt(hotel);
        return roomRepository.save(room);
    }

    public RoomService(RoomRepository roomRepository, RestTemplate restTemplate) {
        this.roomRepository = roomRepository;
        this.restTemplate = restTemplate;
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

//    public void bookRoom(long Id){
//        Room room = roomRepository.findById(Id).orElseThrow(RuntimeException::new);
//        room.isBooked();
//        roomRepository.save(room);
//    }

//     get the hotel information
    public Hotel getHotelInfo(Long id){
        Hotel hotel = roomRepository.findById(id).orElseThrow(RuntimeException::new)
                .getHotel();
        final String url = "http://localhost:8080/hotels/" + hotel.getId();

        return restTemplate.getForObject(url, Hotel.class);
    }
}
