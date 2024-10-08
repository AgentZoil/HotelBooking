package com.example.csci318.hotelbooking.model;

import com.example.csci318.hotelbooking.model.event.UserEvent;
import jakarta.persistence.*;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.Objects;

@Entity
//@Table(name = "users") // Rename the table to 'users'
public class Users extends AbstractAggregateRoot<Users> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;

    // Getters and Setters
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Users() {
    }

    // equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users user = (Users) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(phoneNumber, user.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, phoneNumber);
    }

    public void makeBooking(String hoteName, String roomNumber) {
        UserEvent userEvent = new UserEvent();
        userEvent.setEventName("The user has made a booking at " + hoteName + " room number: " + roomNumber);
        userEvent.setEmail(this.getEmail());
        userEvent.setName(this.getName());
        userEvent.setPassword(this.getPassword());
        userEvent.setPhoneNumber(this.getPhoneNumber());

        System.out.println(userEvent.toString());

        // Register the event
        registerEvent(userEvent);
    }

    // toString method
    @Override
    public String toString() {
        return "User{" +
                "userID=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

}

