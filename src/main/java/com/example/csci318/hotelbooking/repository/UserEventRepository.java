package com.example.csci318.hotelbooking.repository;

import com.example.csci318.hotelbooking.model.event.UserEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEventRepository extends JpaRepository<UserEvent, Long> {
}
