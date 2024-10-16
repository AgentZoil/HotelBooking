package com.example.csci318.interfaces.rest.dto;

public class BookingsByHotel {
    private String hotelName;
    private Long bookingQuantity;

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Long getBookingQuantity() {
        return bookingQuantity;
    }

    public void setBookingQuantity(Long bookingQuantity) {
        this.bookingQuantity = bookingQuantity;
    }
}
