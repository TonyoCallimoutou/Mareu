package com.example.mareu.service;

import com.example.mareu.model.Participant;
import com.example.mareu.model.Place;
import com.example.mareu.model.Reunion;

import java.util.List;

public class FakeApiService implements ApiService {

    private final List<Reunion> reunions = FakeApiGenerator.generateReunions();
    private final List<Participant> participants= FakeApiGenerator.generateParticipants();
    private final List<Place> places = FakeApiGenerator.generatePlace();

    @Override
    public List<Reunion> getReunion() {
        return reunions;
    }

    @Override
    public void createReunion(Reunion reunion) {
        reunions.add(reunion);
    }

    @Override
    public void deleteReunion(Reunion reunion) {
        reunions.remove(reunion);
    }

    @Override
    public List<Reunion> getReunionsByTime() {
        return reunions;
    }

    @Override
    public List<Reunion> getReunionsByPlace() {
        return reunions;
    }

    @Override
    public List<Participant> getParticipant() {
        return participants;
    }

    @Override
    public List<Place> getPlace() {
        return places;
    }
}
