package com.example.mareu.event;

import com.example.mareu.model.Reunion;

public class DeleteReunionEvent {

    Reunion mReunion;

    public DeleteReunionEvent(Reunion reunion) {
        mReunion = reunion;
    }

    public Reunion getReunion () {
        return mReunion;
    }

}
