package com.example.csci318.hotelbooking.domain.service;

import com.example.csci318.hotelbooking.domain.event.BookingEvent;
import com.example.csci318.hotelbooking.domain.event.HotelEvent;
import com.example.csci318.hotelbooking.domain.event.RoomEvent;
import com.example.csci318.hotelbooking.domain.event.UserEvent;
import com.example.csci318.hotelbooking.infrastructure.repository.BookingEventRepository;
import com.example.csci318.hotelbooking.infrastructure.repository.HotelEventRepository;
import com.example.csci318.hotelbooking.infrastructure.repository.RoomEventRepository;
import com.example.csci318.hotelbooking.infrastructure.repository.UserEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class EventHandler {
    private final RoomEventRepository roomEventRepository;
    private final UserEventRepository userEventRepository;
    private final HotelEventRepository hotelEventRepository;
    private final BookingEventRepository bookingEventRepository;

    @Autowired
    EventHandler(RoomEventRepository roomEventRepository, UserEventRepository userEventRepository, HotelEventRepository hotelEventRepository, BookingEventRepository bookingEventRepository){
        this.roomEventRepository = roomEventRepository;
        this.userEventRepository = userEventRepository;
        this.hotelEventRepository = hotelEventRepository;
        this.bookingEventRepository = bookingEventRepository;
    }

    @EventListener
    public void handleUserEvent(UserEvent userEvent){
        System.out.println("User event received: " + userEvent.getEventName());
        userEventRepository.save(userEvent);
    }

    @EventListener
    public void handleRoomEvent(RoomEvent roomEvent){
        System.out.println("Room event received: " + roomEvent.getEventName());
        roomEventRepository.save(roomEvent);
    }

    @EventListener
    public void handleHotelEvent(HotelEvent hotelEvent){
        System.out.println("Hotel event received: " + hotelEvent.getEvent_name());
        hotelEventRepository.save(hotelEvent);
    }

    @EventListener
    public void handleBookingEvent(BookingEvent bookingEvent){
        System.out.println("Hotel event received: " + bookingEvent.getEventName());
        bookingEventRepository.save(bookingEvent);
    }
}
