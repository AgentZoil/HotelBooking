package com.example.csci318.hotelbooking.model.event;

import com.example.csci318.hotelbooking.model.Hotel;
import com.example.csci318.hotelbooking.model.Room;
import com.example.csci318.hotelbooking.model.User;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class BookingEvent {
    @Id
    @GeneratedValue
    private long id;
    @Column
    private String eventName;
    @Column
    private User user;
    @Column
    private Hotel hotel;
    @Column
    private Room room;
    @Column
    private Date checkInDate;
    @Column
    private Date checkOutDate;

    public BookingEvent(){
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    @Override
    public String toString() {
        return "BookingEvent{" +
                "eventName='" + eventName + '\'' +
                ", user=" + user +
                ", hotel=" + hotel +
                ", room=" + room +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                '}';
    }
}
