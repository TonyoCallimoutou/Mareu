package com.example.mareu.service;

import com.example.mareu.model.Participant;
import com.example.mareu.model.Place;
import com.example.mareu.model.Reunion;

import java.util.Calendar;
import java.util.List;

public class FakeApiService implements ApiService {

    private final List<Reunion> reunions = FakeApiGenerator.generateReunions();
    private final List<Participant> participants= FakeApiGenerator.generateParticipants();
    private final List<Place> places = FakeApiGenerator.generatePlace();

    @Override
    public List<Reunion> getReunions() {
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
    public List<Reunion> getReunionsByTime(Calendar date) {
        List<Reunion> reunionsByTime = FakeApiGenerator.generateReunions();
        reunionsByTime.clear();


        for (int i=0; i<reunions.size(); i++) {
            if (reunions.get(i).equalTime(date)) {
                reunionsByTime.add(reunions.get(i));
            }
        }
        return reunionsByTime;
    }

    @Override
    public List<Reunion> getReunionsByPlace(Place place) {
        List<Reunion> reunionsByPlace = FakeApiGenerator.generateReunions();
        reunionsByPlace.clear();


        for (int i=0; i<reunions.size(); i++) {
            if (reunions.get(i).getPlace().equals(place)) {
                reunionsByPlace.add(reunions.get(i));
            }
        }
        return reunionsByPlace;
    }

    @Override
    public List<Participant> getParticipants() {
        return participants;
    }

    @Override
    public List<Place> getPlaces() {
        return places;
    }

    @Override
    public List<Participant> getParticipantsEmpty() {
        List<Participant> listVide = FakeApiGenerator.generateParticipants();
        listVide.clear();
        return  listVide;
    }
}
