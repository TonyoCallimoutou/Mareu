package com.example.mareu.service;

import com.example.mareu.model.Participant;
import com.example.mareu.model.Place;
import com.example.mareu.model.Reunion;

import java.util.List;

public interface ApiService {

    // Reunion
    /**
     * Get list of Reunion
     */
    List<Reunion> getReunion();

    /**
     * Add new reunion
     */
    void createReunion(Reunion reunion);

    /**
     * Remove reunion
     */
    void deleteReunion(Reunion reunion);

    /**
     * filter by hour
     */
    List<Reunion> getReunionsByTime();

    /**
     * filter by place
     */
    List<Reunion> getReunionsByPlace();

    // Participant
    /**
     * Get list of Employee
     */
    List<Participant> getParticipant();

    // Place
    /**
     * Get list of Place
     */
    List<Place> getPlace();

}
