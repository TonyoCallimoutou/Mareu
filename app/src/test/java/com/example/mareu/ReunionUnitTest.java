package com.example.mareu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import com.example.mareu.di.DI;
import com.example.mareu.model.Participant;
import com.example.mareu.model.Place;
import com.example.mareu.model.Reunion;
import com.example.mareu.service.ApiService;
import com.example.mareu.service.FakeApiGenerator;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.List;

/**
 * Unit test on Reunion service
 */
public class ReunionUnitTest {

    private ApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getReunionsWithSuccess() {
        List<Reunion> reunions = service.getReunions();
        List<Reunion> expectedReunions = FakeApiGenerator.FAKE_REUNION;

        assertEquals(reunions, expectedReunions);
    }

    @Test
    public void deleteReunionWithSuccess() {
        Reunion reunionToDelete = service.getReunions().get(0);

        service.deleteReunion(reunionToDelete);

        assertFalse(service.getReunions().contains(reunionToDelete));
    }

    @Test
    public void createReunionWithSuccess() {
        Reunion reunionToCreate = service.getReunions().get(0);
        service.getReunions().clear();

        service.createReunion(reunionToCreate);

        assertTrue(service.getReunions().contains(reunionToCreate));
        assertEquals(1,service.getReunions().size());
    }

    @Test
    public void getParticipantsWithSuccess() {
        List<Participant> participants = service.getParticipants();
        List<Participant> expectedParticipants = FakeApiGenerator.FAKE_PARTICIPANT;

        assertEquals(participants,expectedParticipants);
    }

    @Test
    public void getPlacesWithSuccess() {
        List<Place> places = service.getPlaces();
        List<Place> expectedPlaces = FakeApiGenerator.FAKE_PLACE;

        assertEquals(places,expectedPlaces);

    }

    @Test
    public void getReunionsByTimeWithSuccess() {
        Calendar date0 = service.getReunions().get(0).getTime();
        Calendar date1 = service.getReunions().get(1).getTime();
        Calendar date2 = service.getReunions().get(2).getTime();

        assertNotSame(date0, date1);
        assertNotSame(date1, date2);

        service.getReunions().get(2).setTime(date1);

        List<Reunion> reunionsByTime0 = service.getReunionsByTime(date0);
        List<Reunion> reunionsByTime2 = service.getReunionsByTime(date1);

        assertEquals(1,reunionsByTime0.size());
        assertEquals(2,reunionsByTime2.size());
    }

    @Test
    public void getReunionsByPlaceWithSuccess() {
        Place placeA = service.getPlaces().get(0);

        List<Reunion> reunionsByPlace = service.getReunionsByPlace(placeA);

        assertEquals(1,reunionsByPlace.size());
    }
}