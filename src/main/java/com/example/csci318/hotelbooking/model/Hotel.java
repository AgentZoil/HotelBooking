package com.example.csci318.hotelbooking.model;

import com.example.csci318.hotelbooking.model.event.HotelEvent;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Hotel extends AbstractAggregateRoot<Hotel> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private String description;
    private double pricePerNight;

    @OneToMany(mappedBy = "hotel", orphanRemoval = true)
    @JsonManagedReference  // This prevents the infinite recursion
    private List<Room> rooms; // create a list of rooms
    private List<Long> availableRooms = new ArrayList<>(); // create a list of available rooms

    // Constructors, Getters, and Setters

    public Hotel() {
    }

    public Hotel(String name, String location, String description, double pricePerNight) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.pricePerNight = pricePerNight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<Long> getAvailableRooms(){
        return availableRooms;
    }

    public void addAvailableRoom(Long roomId){
        availableRooms.add(roomId);
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return Double.compare(hotel.pricePerNight, pricePerNight) == 0 &&
                Objects.equals(id, hotel.id) &&
                Objects.equals(name, hotel.name) &&
                Objects.equals(location, hotel.location) &&
                Objects.equals(description, hotel.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, location, description, pricePerNight);
    }

    public void makeBooking(String userName){
        HotelEvent hotelEvent = new HotelEvent();
        hotelEvent.setUserName(userName);
        hotelEvent.setName(this.getName());
        hotelEvent.setEvent_name("This hotel has been visited by " + userName);
        hotelEvent.setDescription(this.getDescription());
        hotelEvent.setLocation(this.getLocation());
        hotelEvent.setDescription(this.getDescription());
        hotelEvent.setPricePerNight(this.getPricePerNight());

        System.out.println(hotelEvent.toString());

        registerEvent(hotelEvent);
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "hotelID=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", pricePerNight=" + pricePerNight + '\'' +
                ", availableRoom=" + availableRooms.toString() +
                '}';
    }
}
