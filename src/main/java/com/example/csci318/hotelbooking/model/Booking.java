package com.example.csci318.hotelbooking.model;

//import com.example.csci318.hotelbooking.model.event.BookingEvent;
import jakarta.persistence.*;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.Date;
import java.util.Objects;

@Entity
public class Booking extends AbstractAggregateRoot<Booking> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    public Booking() {
    }

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    private Date checkInDate;
    private Date checkOutDate;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    // toString method
    @Override
    public String toString() {
        return "Booking{" +
                "bookingID=" + id +
                ", user=" + user +
                ", hotel=" + hotel +
                ", room=" + room +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                '}';
    }

    // equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(id, booking.id) &&
                Objects.equals(user, booking.user) &&
                Objects.equals(hotel, booking.hotel) &&
                Objects.equals(room, booking.room) &&
                Objects.equals(checkInDate, booking.checkInDate) &&
                Objects.equals(checkOutDate, booking.checkOutDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, hotel, room, checkInDate, checkOutDate);
    }
}
