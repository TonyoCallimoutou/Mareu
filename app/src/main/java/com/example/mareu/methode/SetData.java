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

    public void getFilter(Context context, Spinner spinnerFilter) {
        ArrayAdapter<String> filterAdapter = new ArrayAdapter<String>(context,
                R.layout.support_simple_spinner_dropdown_item,filter);
        filterAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spinnerFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if ( i == 1 ) {
                    createDatePickerDialog(context);
                }
                else if ( i == 2 ) {
                    createAlertDialog(context);
                }
                else {
                    //configureAndShowFragment(position,null);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerFilter.setAdapter(filterAdapter);

    }

    public void createDatePickerDialog(Context context) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());


        int selectedYear = calendar.get(Calendar.YEAR);;
        int selectedMonth = calendar.get(Calendar.MONTH);
        int selectedDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);


        // Date Select Listener.
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {

                System.out.println("years = " + year + " - Month of year = "+ (monthOfYear + 1) + " - Day = "+dayOfMonth);


                //configureAndShowFragment(1,date);

            }
        };

        // Create DatePickerDialog (Spinner Mode):
        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                dateSetListener, selectedYear, selectedMonth, selectedDayOfMonth);

        // Show
        datePickerDialog.show();
    }

    public void createAlertDialog (Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("Choisir une Salle");

        builder.setItems(Integer.parseInt(places.toString()), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //configureAndShowFragment(2,salle);
                dialogInterface.dismiss();
            }
        });

        // Create AlertDialog:
        AlertDialog alert = builder.create();
        alert.show();

    }


    public Participant[] getParticipants() {
        return participants;
    }

    public void setParticipants(Participant[] participants) {
        this.participants = participants;
    }

    public Place[] getPlaces() {
        return places;
    }

    public void setPlaces(Place[] places) {
        this.places = places;
    }
}
