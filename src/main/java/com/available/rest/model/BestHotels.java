package com.available.rest.model;

public class BestHotels {
    private static final long serialVersionUID = 2L;


    private String hotelName;
    private int rate; // Number from 1-5
    private double price; // Total price rounded to 2 decimals
    private String roomAmenities; //String of amenities separated by comma



    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getRoomAmenities() {
        return roomAmenities;
    }

    public void setRoomAmenities(String roomAmenities) {
        this.roomAmenities = roomAmenities;
    }


}
