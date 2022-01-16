package com.example.mareu.methode;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.mareu.R;
import com.example.mareu.model.Participant;
import com.example.mareu.model.Place;
import com.example.mareu.model.Reunion;
import com.example.mareu.service.ApiService;
import com.example.mareu.ui.reunion_list.ListReunionActivity;
import com.example.mareu.ui.reunion_list.ReunionFragment;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SetData {

    Place[] places;
    Participant[] participants;
    String[] filter;

    public SetData(ApiService api) {
        places = new Place[api.getPlace().size()];
        for (int i = 0; i < api.getPlace().size(); i++) {
            places[i] = api.getPlace().get(i);
        }

        participants = new Participant[api.getParticipant().size()];
        for (int i = 0; i< api.getParticipant().size(); i++) {
            participants[i] = api.getParticipant().get(i);
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
