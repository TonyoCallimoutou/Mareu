package com.example.mareu.ui.reunion_list;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.mareu.R;
import com.example.mareu.di.DI;
import com.example.mareu.methode.SetData;
import com.example.mareu.model.Place;
import com.example.mareu.model.Reunion;
import com.example.mareu.service.ApiService;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AddReunionFragmentPlace extends Fragment {

    @BindView(R.id.spinner_place)
    Spinner mSpinnerPlace;
    @BindView(R.id.input_topic)
    TextInputLayout topicInput;

    ApiService mApiService;
    SetData mSetData;
    Reunion mReunion;

    public AddReunionFragmentPlace() {

    }
    public AddReunionFragmentPlace(Reunion reunion) {
        mReunion = reunion;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mApiService = DI.getReunionApiService();

        mSetData = new SetData(mApiService);

        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_add_reunion_place, container, false);

        ButterKnife.bind(this,view);

        getSpinnerPlaces();

        setTextInput();

        return view;
    }

    public void getSpinnerPlaces() {

        ArrayAdapter<Place> placeAdapter = new ArrayAdapter<Place>(getContext(),
                R.layout.spinner_item_black, mSetData.getArrayPlaces());

        placeAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);


        mSpinnerPlace.setAdapter(placeAdapter);

        mSpinnerPlace.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mReunion.setPlace((Place) mSpinnerPlace.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void setTextInput() {
        topicInput.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mReunion.setTopic(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

}