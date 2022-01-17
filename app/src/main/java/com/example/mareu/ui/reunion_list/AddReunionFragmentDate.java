package com.example.mareu.ui.reunion_list;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.mareu.R;
import com.example.mareu.di.DI;
import com.example.mareu.model.Reunion;
import com.example.mareu.service.ApiService;

import java.util.Calendar;
import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;


@RequiresApi(api = Build.VERSION_CODES.M)
public class AddReunionFragmentDate extends Fragment {


    @BindView(R.id.picker_date)
    DatePicker pickerDate;
    @BindView(R.id.picker_time)
    TimePicker pickerTime;


    int year ;
    int month ;
    int day ;
    int hour ;
    int minute ;

    Calendar date ;


    ApiService mApiService;
    Reunion mReunion;


    public AddReunionFragmentDate() {

    }

    public AddReunionFragmentDate(Reunion reunion) {
        mReunion = reunion;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mApiService = DI.getReunionApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_reunion_date, container, false);

        ButterKnife.bind(this,view);

        initPicker();

        return view;
    }

    public void initPicker() {

        pickerTime.setIs24HourView(true);

        if (mReunion.getTime() == null) {
            year = pickerDate.getYear();
            month = pickerDate.getMonth();
            day = pickerDate.getDayOfMonth();
            hour = pickerTime.getHour();
            minute = pickerTime.getMinute();
        }
        else {

            year = mReunion.getTime().get(Calendar.YEAR);
            month = mReunion.getTime().get(Calendar.MONTH);
            day = mReunion.getTime().get(Calendar.DAY_OF_MONTH);
            hour = mReunion.getTime().get(Calendar.HOUR_OF_DAY);
            minute = mReunion.getTime().get(Calendar.MINUTE);

            pickerTime.setCurrentHour(hour);
            pickerTime.setCurrentMinute(minute);
        }

        pickerDate.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                year = i;
                month = i1;
                day = i2;

                date = new GregorianCalendar(year,month,day,hour,minute);
                mReunion.setTime(date);

            }
        });

        pickerTime.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                hour = i;
                minute = i1;

                date = new GregorianCalendar(year,month,day,hour,minute);
                mReunion.setTime(date);
            }
        });

        date = new GregorianCalendar(year,month,day,hour,minute);
        mReunion.setTime(date);

    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);


        LayoutInflater inflater = LayoutInflater.from(getActivity());

        populateViewForOrientation(inflater, (ViewGroup) getView());

        pickerDate = getView().findViewById(R.id.picker_date);
        pickerTime = getView().findViewById(R.id.picker_time);

        initPicker();

    }


    public void populateViewForOrientation(LayoutInflater inflater, ViewGroup viewGroup) {
        viewGroup.removeAllViewsInLayout();
        inflater.inflate(R.layout.fragment_add_reunion_date,viewGroup);
    }


}