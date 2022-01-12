package com.example.mareu.model;

import android.annotation.SuppressLint;
import android.text.Editable;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Reunion implements Serializable {

    /** Identifier */
    private long id;

    /** Time */
    private Calendar time;

    /** Place */
    private Place place;

    /** Topic */
    private String topic;

    /** participant */
    private List<Participant> participant;

    /**
     * Constructor
     */
    public Reunion(long id, Calendar time, Place place, String topic, List<Participant> participant) {
        this.id = id;
        this.time = time;
        this.place = place;
        this.topic = topic;
        this.participant = participant;
    }

    /**
     * Getter and Setter
     */
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Calendar getTime() {
        return time;
    }

    public String getStringTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm");

        if (time == null) {
            return null;
        }

        return dateFormat.format(time.getTime());
    }

    public void setTime(Calendar time) {
        this.time = time;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public List<Participant> getParticipant() {
        return participant;
    }

    public void setParticipant(List<Participant> participant) {
        this.participant = participant;
    }

    public boolean equalTime(Calendar date) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String string1 = dateFormat.format(time.getTime());
        String string2 = dateFormat.format(date.getTime());

        if (string1.equals(string2))  {
            return true;
        }
        else {
            return false;
        }
    }

}
