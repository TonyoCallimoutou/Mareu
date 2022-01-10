package com.example.mareu;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.example.mareu.di.DI;
import com.example.mareu.model.Participant;
import com.example.mareu.model.Place;
import com.example.mareu.model.Reunion;
import com.example.mareu.service.ApiService;
import com.example.mareu.service.FakeApiGenerator;

import java.util.List;

/**
 * Unit test on Reunion service
 */
public class ExampleUnitTest {

    private ApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getReunionsWithSuccess() {
        List<Reunion> reunions = service.getReunion();
        List<Reunion> expectedReunions = FakeApiGenerator.FAKE_REUNION;

        assertEquals(reunions, expectedReunions);
    }

    @Test
    public void deleteReunionWithSuccess() {
        Reunion reunionToDelete = service.getReunion().get(0);

        service.deleteReunion(reunionToDelete);

        assertFalse(service.getReunion().contains(reunionToDelete));
    }

    @Test
    public void createReunionWithSuccess() {
        Reunion reunionToCreate = service.getReunion().get(0);
        service.getReunion().clear();

        service.createReunion(reunionToCreate);

        assertTrue(service.getReunion().contains(reunionToCreate));
        assertEquals(1,service.getReunion().size());
    }

    @Test
    public void getParticipantsWithSuccess() {
        List<Participant> participants = service.getParticipant();
        List<Participant> expectedParticipants = FakeApiGenerator.FAKE_PARTICIPANT;

        assertEquals(participants,expectedParticipants);
    }

    @Test
    public void getPlacesWithSuccess() {
        List<Place> places = service.getPlace();
        List<Place> expectedPlaces = FakeApiGenerator.FAKE_PLACE;

        assertEquals(places,expectedPlaces);

    }

    @Test
    public void getReunionsByTimeWithSuccess() {
    }

    @Test
    public void getReunionsByPlaceWithSuccess() {
    }
}