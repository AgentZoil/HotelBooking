package com.example.csci318.hotelbooking.repository;

import com.example.csci318.hotelbooking.model.event.UserEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEventRepository extends JpaRepository<UserEvent, Long> {
}
