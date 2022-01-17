package com.example.mareu.service;

import com.example.mareu.model.Participant;
import com.example.mareu.model.Place;
import com.example.mareu.model.Reunion;

import java.util.Calendar;
import java.util.List;

public interface ApiService {

    // Reunion
    /**
     * Get list of Reunion
     */
    List<Reunion> getReunions();

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
    List<Reunion> getReunionsByTime(Calendar date);

    /**
     * filter by place
     */
    List<Reunion> getReunionsByPlace(Place place);

    // Participant
    /**
     * Get list of Employee
     */
    List<Participant> getParticipants();

    // Place
    /**
     * Get list of Place
     */
    List<Place> getPlaces();

    /**
     * Get Empty list of Employee
     */
    List<Participant> getParticipantsEmpty();

}
