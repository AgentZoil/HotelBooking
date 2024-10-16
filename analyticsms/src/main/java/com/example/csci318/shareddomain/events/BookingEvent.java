package com.example.csci318.shareddomain.events;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class BookingEvent {
    BookingEventData bookingEventData;

    public BookingEvent(){}

    public BookingEvent(BookingEventData bookingEventData){
        this.bookingEventData = bookingEventData;
    }

    public BookingEventData getBookingEventData() {
        return bookingEventData;
    }

    public void setBookingEventData(BookingEventData bookingEventData) {
        this.bookingEventData = bookingEventData;
    }

    @Override
    public String toString() {
        return "BookingEvent{" +
                "bookingEventData=" + bookingEventData +
                '}';
    }
}
