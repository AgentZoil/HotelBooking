package com.example.csci318.shareddomain.events;

import jakarta.persistence.*;

@Entity
public class HotelEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    @Column
    private String event_name;
    @Column
    private String name;
    @Column
    private String location;
    @Column
    private String description;
    @Column
    private double pricePerNight;
    @Column
    private String userName;

    public HotelEvent() {
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

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    @Override
    public String toString() {
        return "HotelEvent{" +
                "Id=" + Id +
                ", event_name='" + event_name + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", pricePerNight=" + pricePerNight +
                ", userName='" + userName + '\'' +
                '}';
    }
}
