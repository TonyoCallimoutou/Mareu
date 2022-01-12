package com.example.mareu;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.example.mareu.di.DI;
import com.example.mareu.model.Reunion;
import com.example.mareu.service.ApiService;

import java.io.Serializable;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddReunionFragmentDate#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddReunionFragmentDate extends Fragment {


    @BindView(R.id.picker_date)
    DatePicker pickerDate;
    @BindView(R.id.picker_time)
    TimePicker pickerTime;


    ApiService mApiService;
    AddReunionFragmentPage1 fragmentPage1;
    AddReunionFragmentParticipant fragmentPageParticipant;

    Bundle bundle;
    private static final String REUNION = "REUNION";
    Reunion mReunion;


    Calendar calendar;
    int year;
    int month;
    int day;
    int hour;
    int minute;


    public static AddReunionFragmentDate newInstance() {
        AddReunionFragmentDate fragment = new AddReunionFragmentDate();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mApiService = DI.getReunionApiService();

        bundle = new Bundle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_reunion_part3, container, false);

        ButterKnife.bind(this,view);

        mReunion = (Reunion) getArguments().getSerializable(REUNION);


        initDatePicker();

        initTimePicker();

        return view;
    }


    public void initDatePicker() {
        if (mReunion.getTime() == null) {
            calendar = Calendar.getInstance();
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);
        }
        else {
            year = mReunion.getTime().get(Calendar.YEAR);
            month = mReunion.getTime().get(Calendar.MONTH);
            day = mReunion.getTime().get(Calendar.DAY_OF_MONTH);
        }

        pickerDate.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {

            }
        });
    }

    public void initTimePicker() {

        pickerTime.setIs24HourView(true);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public void initData() {

        year = pickerDate.getYear() ;
        month = pickerDate.getMonth();
        day = pickerDate.getDayOfMonth();
        hour = pickerTime.getHour();
        minute = pickerTime.getMinute();

        Calendar date = Calendar.getInstance();

        date.set(Calendar.YEAR, year);
        date.set(Calendar.MONTH,month);
        date.set(Calendar.DAY_OF_MONTH,day);
        date.set(Calendar.HOUR,hour);
        date.set(Calendar.MINUTE,minute);

        mReunion.setTime(date);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @OnClick(R.id.page3)
    void goToPage3() {

        initData();

        fragmentPageParticipant = new AddReunionFragmentParticipant().newInstance();
        bundle.putSerializable(REUNION,(Serializable) mReunion);
        fragmentPageParticipant.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_add_fragment,fragmentPageParticipant)
                .commit();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @OnClick(R.id.page1)
    void goToPage1() {

        initData();

        fragmentPage1 = new AddReunionFragmentPage1().newInstance();
        bundle.putSerializable(REUNION,(Serializable) mReunion);
        fragmentPage1.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_add_fragment,fragmentPage1)
                .commit();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @OnClick(R.id.create)
    void addReunion() {
        initData();
        mApiService.createReunion(mReunion);
        getActivity().finish();
    }

}