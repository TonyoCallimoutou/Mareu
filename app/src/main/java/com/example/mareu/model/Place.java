package com.example.mareu.model;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place1 = (Place) o;
        return Objects.equals(place, place1.place);
    }

    @Override
    public int hashCode() {
        return Objects.hash(place);
    }
}
