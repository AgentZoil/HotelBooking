package com.example.csci318.hotelbooking.application.service;

import com.example.csci318.hotelbooking.domain.event.BookingEvent;
import com.example.csci318.hotelbooking.domain.model.Booking;
import com.example.csci318.hotelbooking.infrastructure.messaging.KafkaProducer;
import com.example.csci318.hotelbooking.infrastructure.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class BookingApplicationService {
    @Autowired
    private KafkaProducer kafkaProducer;
    @Autowired
    private BookingRepository bookingRepository;

    public Booking createBooking(Booking booking){
        // save booking to db and publish the event
        bookingRepository.save(booking);

        BookingEvent event = new BookingEvent(booking.getHotel().getName(), booking.getUser().getName(), booking.getRoom().getRoomNumber(), booking.getCheckInDate(), booking.getCheckOutDate());

        kafkaProducer.sendBookingEvent(event);
        return booking;
    }
}
