package com.example.mareu.model;

public class Participant {

    /** Mail */
    private String mail;

    /**
     * Constructor
     */
    public Participant(String mail) {
        this.mail = mail;
    }

    /**
     * Getter and Setter
     */
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return  mail ;
    }
}
