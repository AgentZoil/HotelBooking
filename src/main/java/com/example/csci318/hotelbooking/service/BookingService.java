package com.example.csci318.hotelbooking.service;

import com.example.csci318.hotelbooking.model.Booking;
import com.example.csci318.hotelbooking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    // Get all bookings
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // Get booking by ID
    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    // Create a new booking
    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    // Update an existing booking
    public Optional<Booking> updateBooking(Long id, Booking bookingDetails) {
        return bookingRepository.findById(id).map(booking -> {
            booking.setCheckInDate(bookingDetails.getCheckInDate());
            booking.setCheckOutDate(bookingDetails.getCheckOutDate());
            booking.setHotel(bookingDetails.getHotel());
            booking.setRoom(bookingDetails.getRoom());
            return bookingRepository.save(booking);
        });
    }

    // Delete a booking
    public Optional<Void> deleteBooking(Long id) {
        return bookingRepository.findById(id).map(booking -> {
            bookingRepository.delete(booking);
            return null;
        });
    }
}
