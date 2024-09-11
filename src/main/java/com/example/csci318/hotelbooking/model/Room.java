package com.example.csci318.hotelbooking.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomNumber;
    private String type;
    private double price;
    private boolean availability;

    @ManyToOne
//    @JoinColumn(name = "hotel_id", nullable = false)
    @JsonBackReference  // This prevents the infinite recursion
    private Hotel hotel;

    // Constructors, Getters, and Setters

    public Room() {
    }

//    public Room(String roomNumber, String type, double price, boolean availability, Hotel hotel) {
//        this.roomNumber = roomNumber;
//        this.type = type;
//        this.price = price;
//        this.availability = availability;
//        this.hotel = hotel;
//    }

    public Room(String roomNumber, String type, double price, boolean availability, Hotel hotel) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
        this.availability = availability;
        this.hotel = hotel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Double.compare(room.price, price) == 0 &&
                availability == room.availability &&
                Objects.equals(id, room.id) &&
                Objects.equals(roomNumber, room.roomNumber) &&
                Objects.equals(type, room.type) &&
                Objects.equals(hotel, room.hotel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roomNumber, type, price, availability, hotel);
    }

    @Override
//    public String toString() {
//        return "Room{" +
//                "roomID=" + id +
//                ", roomNumber='" + roomNumber + '\'' +
//                ", type='" + type + '\'' +
//                ", price=" + price +
//                ", availability=" + availability +
//                ", hotel=" + hotel.getName() +  // Avoid printing the entire hotel object to prevent recursion
//                '}';
//    }

    public String toString() {
        return "Room{" +
                "roomID=" + id +
                ", roomNumber='" + roomNumber + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", availability=" + availability +
                '}';
    }
}
