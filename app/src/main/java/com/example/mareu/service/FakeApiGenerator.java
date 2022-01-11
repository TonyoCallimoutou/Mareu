package com.example.mareu.service;

import com.example.mareu.model.Participant;
import com.example.mareu.model.Place;
import com.example.mareu.model.Reunion;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FakeApiGenerator {

    public static List<Place> FAKE_PLACE = Arrays.asList(
            new Place("Salle A"),
            new Place("Salle B"),
            new Place("Salle C"),
            new Place("Salle D"),
            new Place("Salle E")
    );

    public static List<Participant> FAKE_PARTICIPANT = Arrays.asList(
            new Participant("maxime@lamazone.com"),
            new Participant("alex@lamazone.com"),
            new Participant("paul@lamazone.com"),
            new Participant("viviane@lamazone.com"),
            new Participant("amandine@lamazone.com"),
            new Participant("luc@lamazone.com")
    );

    public static List<Participant> PARTICIPANT1 = Arrays.asList(
            FAKE_PARTICIPANT.get(0),
            FAKE_PARTICIPANT.get(1)
    );

    public static List<Participant> PARTICIPANT2 = Arrays.asList(
            FAKE_PARTICIPANT.get(2),
            FAKE_PARTICIPANT.get(3)
    );

    public static List<Participant> PARTICIPANT3 = Arrays.asList(
            FAKE_PARTICIPANT.get(4),
            FAKE_PARTICIPANT.get(5)
    );

    public static Calendar date = Calendar.getInstance();

    public static List<Reunion> FAKE_REUNION = Arrays.asList(
            new Reunion(001,date,FAKE_PLACE.get(0),"Peach",PARTICIPANT1),
            new Reunion(002,date,FAKE_PLACE.get(1),"Mario",PARTICIPANT2),
            new Reunion(003,date,FAKE_PLACE.get(2),"Luigi",PARTICIPANT3)
    );

    static List<Reunion> generateReunions() {
        return new ArrayList<>(FAKE_REUNION);
    }
    static List<Participant> generateParticipants() {
        return new ArrayList<>(FAKE_PARTICIPANT);
    }
    static List<Place> generatePlace() {
        return new ArrayList<>(FAKE_PLACE);
    }

}
