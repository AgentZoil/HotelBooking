package com.example.csci318.hotelbooking.service;

import com.example.csci318.hotelbooking.model.Hotel;
import com.example.csci318.hotelbooking.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

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
    public Optional<Void> deleteHotel(Long id) {
        return hotelRepository.findById(id).map(hotel -> {
            hotelRepository.delete(hotel);
            return null;
        });
    }
}
