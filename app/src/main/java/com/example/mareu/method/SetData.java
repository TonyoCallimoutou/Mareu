package com.example.mareu.method;

import com.example.mareu.model.Participant;
import com.example.mareu.model.Place;
import com.example.mareu.service.ApiService;

public class SetData {

    Place[] places;
    Participant[] participants;
    String[] filter;

    public SetData(ApiService api) {
        places = new Place[api.getPlaces().size()];
        for (int i = 0; i < api.getPlaces().size(); i++) {
            places[i] = api.getPlaces().get(i);
        }

        participants = new Participant[api.getParticipants().size()];
        for (int i = 0; i< api.getParticipants().size(); i++) {
            participants[i] = api.getParticipants().get(i);
        }

        filter = new String[] {"Tout", "Filter by time", "Filter by place"};
    }

    public Place[] getArrayPlaces() {
        return places;
    }

    public void setArrayPlaces(Place[] places) {
        this.places = places;
    }

    public String[] getStringArrayPlaces() {
        String[] newArray = new String[places.length];
        for (int i=0; i<places.length; i++) {
            newArray[i] = places[i].toString();
        }
        return newArray;
    }

    public Participant[] getArrayParticipants() {

        return participants;
    }

    public String[] getStringParticipants() {
        String[] newArray = new String[participants.length+1];
        newArray[0] = "Add all";
        for (int i=0; i<participants.length; i++) {
            newArray[i+1] = participants[i].toString();
        }
        return newArray;
    }



    public void setArrayParticipants(Participant[] participants) {
        this.participants = participants;
    }

    public String[] getArrayFilter() {
        return filter;
    }

    public void setArrayFilter(String[] filter) {
        this.filter = filter;
    }
}
