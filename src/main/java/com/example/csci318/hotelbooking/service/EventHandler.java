package com.example.csci318.hotelbooking.service;

import com.example.csci318.hotelbooking.model.event.HotelEvent;
import com.example.csci318.hotelbooking.model.event.RoomEvent;
import com.example.csci318.hotelbooking.model.event.UserEvent;
import com.example.csci318.hotelbooking.repository.HotelEventRepository;
import com.example.csci318.hotelbooking.repository.RoomEventRepository;
import com.example.csci318.hotelbooking.repository.UserEventRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class EventHandler {
    private final RoomEventRepository roomEventRepository;
    private final UserEventRepository userEventRepository;
    private final HotelEventRepository hotelEventRepository;

    @Autowired
    EventHandler(RoomEventRepository roomEventRepository, UserEventRepository userEventRepository, HotelEventRepository hotelEventRepository){
        this.roomEventRepository = roomEventRepository;
        this.userEventRepository = userEventRepository;
        this.hotelEventRepository = hotelEventRepository;
    }

    @TransactionalEventListener
    public void handleUserEvent(UserEvent userEvent){
        System.out.println("User event received: " + userEvent.getEventName());
        userEventRepository.save(userEvent);
    }

    @TransactionalEventListener
    public void handleRoomEvent(RoomEvent roomEvent){
        System.out.println("Room event received: " + roomEvent.getEventName());
        roomEventRepository.save(roomEvent);
    }

    @TransactionalEventListener
    public void handleHotelEvent(HotelEvent hotelEvent){
        System.out.println("Hotel event received: " + hotelEvent.getEvent_name());
        hotelEventRepository.save(hotelEvent);
    }
}
