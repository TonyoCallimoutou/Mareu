package com.example.mareu;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.example.mareu.di.DI;
import com.example.mareu.methode.DateFormatTextWatcher;
import com.example.mareu.methode.SetData;
import com.example.mareu.model.Place;
import com.example.mareu.model.Reunion;
import com.example.mareu.service.ApiService;
import com.google.android.material.textfield.TextInputLayout;

import java.io.Serializable;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddReunionFragmentPart1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddReunionFragmentPart1 extends Fragment {

    @BindView(R.id.spinner_place)
    Spinner mSpinnerPlace;
    @BindView(R.id.input_topic)
    TextInputLayout topicInput;
    @BindView(R.id.editText_birthDay)
    EditText editText;
    @BindView(R.id.picker_time)
    TimePicker pickerTime;

    ApiService mApiService;
    SetData mSetData;
    AddReunionFragmentPart2 fragmentPage2;

    Bundle bundle;
    private static final String REUNION = "REUNION";
    Reunion mReunion;

    public static AddReunionFragmentPart1 newInstance() {
        AddReunionFragmentPart1 fragment = new AddReunionFragmentPart1();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mApiService = DI.getReunionApiService();

        mSetData = new SetData(mApiService);

        bundle = new Bundle();

        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_add_reunion_part1, container, false);

        ButterKnife.bind(this,view);

        mReunion = (Reunion) getArguments().getSerializable(REUNION);

        getSpinnerPlaces();

        TextWatcher textWatcher = new DateFormatTextWatcher(editText);
        editText.addTextChangedListener(textWatcher);


        // Time Picker
        pickerTime.setIs24HourView(true);

        return view;
    }

    public void getSpinnerPlaces() {

        ArrayAdapter<Place> placeAdapter = new ArrayAdapter<Place>(getContext(),
                R.layout.support_simple_spinner_dropdown_item, mSetData.getPlaces());
        placeAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        mSpinnerPlace.setAdapter(placeAdapter);

        mSpinnerPlace.setSelection(getSpinnerIndex(mSpinnerPlace, mReunion.getPlace()));

    }

    //private method of your class
    private int getSpinnerIndex(Spinner spinner, Place place){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).equals(place)){
                return i;
            }
        }

        return 0;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @OnClick(R.id.create)
    void addReunion() {
        initData();
        mApiService.createReunion(mReunion);
        getActivity().finish();
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @OnClick(R.id.page2)
    void goToPage2() {

        initData();

        fragmentPage2 = new AddReunionFragmentPart2().newInstance();
        bundle.putSerializable(REUNION,(Serializable) mReunion);
        fragmentPage2.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_add_fragment,fragmentPage2)
                .commit();
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public void initData() {
        Place place = (Place) mSpinnerPlace.getSelectedItem();
        mReunion.setPlace(place);
        mReunion.setTopic(topicInput.getEditText().getText().toString());

        int year = Integer.parseInt(editText.getText().toString().substring(6,10)) ;

        int month = Integer.parseInt(editText.getText().toString().substring(3,5));
        int day = Integer.parseInt(editText.getText().toString().substring(0,2));
        int hour = pickerTime.getHour();
        int minute = pickerTime.getMinute();

        Calendar date = Calendar.getInstance();

        date.set(Calendar.YEAR, year);
        date.set(Calendar.MONTH,month);
        date.set(Calendar.DAY_OF_MONTH,day);
        date.set(Calendar.HOUR,hour);
        date.set(Calendar.MINUTE,minute);

        mReunion.setTime(date);
    }



}