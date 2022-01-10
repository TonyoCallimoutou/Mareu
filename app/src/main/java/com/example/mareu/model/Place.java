package com.example.mareu.model;

public class Place {
    /** place */
    private String place;

    /**
     * Constructor
     */
    public Place(String place) {
        this.place = place;
    }

    /**
     * Getter and Setter
     */
    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return  place ;
    }
}
