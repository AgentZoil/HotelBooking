package com.example.csci318.hotelbooking.controller;

import com.example.csci318.hotelbooking.model.Hotel;
import com.example.csci318.hotelbooking.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelRepository hotelRepository;

    @GetMapping
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable Long id) {
        return hotelRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Hotel createHotel(@RequestBody Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable Long id, @RequestBody Hotel hotelDetails) {
        return hotelRepository.findById(id)
                .map(hotel -> {
                    hotel.setName(hotelDetails.getName());
                    hotel.setLocation(hotelDetails.getLocation());
                    hotel.setDescription(hotelDetails.getDescription());
                    hotel.setPricePerNight(hotelDetails.getPricePerNight());
                    return ResponseEntity.ok(hotelRepository.save(hotel));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id) {
        return hotelRepository.findById(id)
                .map(hotel -> {
                    hotelRepository.delete(hotel);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
