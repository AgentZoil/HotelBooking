package com.example.csci318.hotelbooking.service;

import com.example.csci318.hotelbooking.model.Hotel;
import com.example.csci318.hotelbooking.model.Room;
import com.example.csci318.hotelbooking.repository.HotelRepository;
import org.apache.tomcat.jni.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;
    private final RestTemplate restTemplate;

    HotelService(HotelRepository hotelRepository, RestTemplate restTemplate){
        this.hotelRepository = hotelRepository;
        this.restTemplate = restTemplate;
    }

    // Get all hotels
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    // Get hotel by ID
    public Optional<Hotel> getHotelById(Long id) {
        return hotelRepository.findById(id);
    }

    // Create a new hotel
    public Hotel createHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    // Update an existing hotel
    public Optional<Hotel> updateHotel(Long id, Hotel hotelDetails) {
        return hotelRepository.findById(id).map(hotel -> {
            hotel.setName(hotelDetails.getName());
            hotel.setLocation(hotelDetails.getLocation());
            hotel.setDescription(hotelDetails.getDescription());
            hotel.setPricePerNight(hotelDetails.getPricePerNight());
            return hotelRepository.save(hotel);
        });
    }

    // Delete a hotel
    public Optional<Void> deleteHotel(Long id){
        return hotelRepository.findById(id).map(hotel -> {
            hotelRepository.delete(hotel);
            return null;
        });
    }

//     Get available rooms
    public List<Room> getAvailableRooms(Long id){
        final String url = "http://localhost:8080/rooms/";
        List<Room> rooms = new ArrayList<>();
        List<Long> ids = hotelRepository.findById(id).orElseThrow(RuntimeException::new)
                .getAvailableRooms();
        for(Long num: ids){
            rooms.add(restTemplate.getForObject(url + num, Room.class));
        }
        return rooms;
    }
}
