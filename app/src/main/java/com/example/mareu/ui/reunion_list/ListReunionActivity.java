package com.example.mareu.ui.reunion_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;

import com.example.mareu.R;
import com.example.mareu.di.DI;
import com.example.mareu.methode.SetData;
import com.example.mareu.service.ApiService;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListReunionActivity extends AppCompatActivity{

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.spinner_filter)
    Spinner mSpinnerFilter;


    ApiService mApiService;
    ReunionFragment reunionFragment;
    SetData setData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reunion_list);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        mApiService = DI.getReunionApiService();

        setData = new SetData(mApiService);

        getFilter();

    }

    public void getFilter() {

        ArrayAdapter<String> filterAdapter = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item,setData.getArrayFilter());


        mSpinnerFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if ( i == 1 ) {
                    createDatePickerDialog();
                }
                else if ( i == 2 ) {
                    createAlertDialog();
                }
                else {
                    initFragment(i,null);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mSpinnerFilter.setAdapter(filterAdapter);

    }

    public void createDatePickerDialog() {

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

                Calendar date = Calendar.getInstance();
                date.set(Calendar.YEAR,year);
                date.set(Calendar.MONTH,monthOfYear);
                date.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                initFragment(1,date);

            }
        };

        // Create DatePickerDialog (Spinner Mode):
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                dateSetListener, selectedYear, selectedMonth, selectedDayOfMonth);

        // Show
        datePickerDialog.show();
    }

    public void createAlertDialog () {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Choisir une Salle");

        builder.setItems(setData.getStringArrayPlaces(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                initFragment(2,setData.getArrayPlaces()[i]);
                dialogInterface.dismiss();
            }
        });

        // Create AlertDialog:
        AlertDialog alert = builder.create();
        alert.show();

    }


    public void initFragment(int filterPosition, Object object) {
        reunionFragment = new ReunionFragment().newInstance(filterPosition,object);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, reunionFragment)
                .commit();
    }


    @OnClick(R.id.fab_add_reunion)
    void addReunion () {
        AddReunionActivity.navigate(this);
    }
}