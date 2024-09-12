package com.example.csci318.hotelbooking.model.event;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class BookingEvent {
    @Id
    @GeneratedValue
    private long id;
    @Column
    private String eventName;
}
