package com.example.csci318.hotelbooking.service;

import com.example.csci318.hotelbooking.model.Booking;
import com.example.csci318.hotelbooking.model.Room;
import com.example.csci318.hotelbooking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private RoomService roomService;

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
        Room room = roomService.getRoomById(booking.getRoom().getId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        // Check if the room is available
        if(!room.isAvailability()){
            throw new RuntimeException("Room is not available for booking");
        }

        // If the room is available, set the room as unavailable and register the event
        room.setAvailability(false);
        room.isBooked();

        roomService.updateRoom(room.getId(), room);

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
        // find the booking
        Booking b = getBookingById(id)
                .orElseThrow(() -> new RuntimeException("Booking is not found"));

        // find the room associated with the booking
        Room room = roomService.getRoomById(b.getRoom().getId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        // set the availability of the room
        room.setAvailability(true);

        // register the event
        room.isReleased();

        // update the room in the repo
        roomService.updateRoom(room.getId(), room);

        return bookingRepository.findById(id).map(booking -> {
            bookingRepository.delete(booking);
            return null;
        });


    }
}
