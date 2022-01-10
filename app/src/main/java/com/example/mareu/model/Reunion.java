package com.example.mareu.model;

import java.util.Date;
import java.util.List;

public class Reunion {

    /** Identifier */
    private long id;

    /** Time */
    private Date time;

    /** Place */
    private Place place;

    /** Topic */
    private String topic;

    /** participant */
    private List<Participant> participant;

    /**
     * Constructor
     */
    public Reunion(long id, Date time, Place place, String topic, List<Participant> participant) {
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
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
