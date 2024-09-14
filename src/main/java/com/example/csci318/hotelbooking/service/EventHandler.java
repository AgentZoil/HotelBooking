package com.example.csci318.hotelbooking.service;

import com.example.csci318.hotelbooking.model.User;
import com.example.csci318.hotelbooking.model.event.RoomEvent;
import com.example.csci318.hotelbooking.model.event.UserEvent;
import com.example.csci318.hotelbooking.repository.RoomEventRepository;
import com.example.csci318.hotelbooking.repository.UserEventRepository;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.support.CustomSQLErrorCodesTranslation;
import org.springframework.stereotype.Service;

@Service
public class EventHandler {
    private final RoomEventRepository roomEventRepository;
    private final UserEventRepository userEventRepository;

    EventHandler(RoomEventRepository roomEventRepository, UserEventRepository userEventRepository){
        this.roomEventRepository = roomEventRepository;
        this.userEventRepository = userEventRepository;
    }

    @EventListener
    public void handleRoomEvent(RoomEvent roomEvent){
        roomEventRepository.save(roomEvent);
    }

    @EventListener
    public void handleUserEvent(UserEvent userEvent){
        userEventRepository.save(userEvent);
    }
}
