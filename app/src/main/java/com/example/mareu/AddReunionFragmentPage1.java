package com.example.mareu;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.mareu.di.DI;
import com.example.mareu.methode.SetData;
import com.example.mareu.model.Place;
import com.example.mareu.model.Reunion;
import com.example.mareu.service.ApiService;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddReunionFragmentPage1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddReunionFragmentPage1 extends Fragment {

    @BindView(R.id.spinner_place)
    Spinner mSpinnerPlace;
    @BindView(R.id.input_topic)
    TextInputLayout topicInput;
    @BindView(R.id.input_topic_edit_text)
    TextInputEditText topicEditText;

    ApiService mApiService;
    SetData mSetData;
    AddReunionFragmentParticipant fragmentPageParticipant;
    AddReunionFragmentDate fragmentPageDate;

    Bundle bundle;
    private static final String REUNION = "REUNION";
    Reunion mReunion;

    public static AddReunionFragmentPage1 newInstance() {
        AddReunionFragmentPage1 fragment = new AddReunionFragmentPage1();
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

        initTopicInput();

        return view;
    }

    public void getSpinnerPlaces() {

        ArrayAdapter<Place> placeAdapter = new ArrayAdapter<Place>(getContext(),
                R.layout.support_simple_spinner_dropdown_item, mSetData.getPlaces());
        placeAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        mSpinnerPlace.setAdapter(placeAdapter);

        mSpinnerPlace.setSelection(getSpinnerIndex(mSpinnerPlace, mReunion.getPlace()));

    }

    public int getSpinnerIndex(Spinner spinner, Place place){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).equals(place)){
                return i;
            }
        }

        return 0;
    }

    public void initTopicInput() {
        if (mReunion.getTopic() != null) {
            topicInput.getEditText().setText(mReunion.getTopic());
        }
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

        fragmentPageDate = new AddReunionFragmentDate().newInstance();
        bundle.putSerializable(REUNION,(Serializable) mReunion);
        fragmentPageDate.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_add_fragment,fragmentPageDate)
                .commit();
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
    public void initData() {
        Place place = (Place) mSpinnerPlace.getSelectedItem();
        mReunion.setPlace(place);
        mReunion.setTopic(topicInput.getEditText().getText().toString());
    }



}