package com.example.mareu.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

}
