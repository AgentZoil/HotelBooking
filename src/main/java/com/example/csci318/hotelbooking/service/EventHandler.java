package com.example.csci318.hotelbooking.service;

import com.example.csci318.hotelbooking.model.event.RoomEvent;
import com.example.csci318.hotelbooking.repository.RoomEventRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class EventHandler {
    private final RoomEventRepository roomEventRepository;

    EventHandler(RoomEventRepository roomEventRepository){
        this.roomEventRepository = roomEventRepository;
    }

    @EventListener
    public void handleRoomEvent(RoomEvent roomEvent){
        roomEventRepository.save(roomEvent);
    }
}
