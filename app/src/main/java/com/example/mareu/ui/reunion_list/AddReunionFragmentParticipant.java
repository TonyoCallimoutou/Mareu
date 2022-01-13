package com.example.mareu.ui.reunion_list;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mareu.R;
import com.example.mareu.di.DI;
import com.example.mareu.methode.SetData;
import com.example.mareu.model.Participant;
import com.example.mareu.model.Reunion;
import com.example.mareu.service.ApiService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddReunionFragmentParticipant extends Fragment {


    @BindView(R.id.list_view_participant)
    ListView listViewParticipant;


    ApiService mApiService;
    SetData mSetData;
    Reunion mReunion;
    List<Participant> listParticipant;

    public AddReunionFragmentParticipant() {

    }

    public AddReunionFragmentParticipant(Reunion reunion) {
        mReunion = reunion;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mApiService = DI.getReunionApiService();
        mSetData = new SetData(mApiService);

        listParticipant = mApiService.getParticipantEmpty();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_reunion_participant, container, false);

        ButterKnife.bind(this,view);


        if (mReunion.getParticipant() != null) {
            listParticipant = mReunion.getParticipant();
        }

        getListViewParticipant();

        return view;
    }

    public void getListViewParticipant() {


        listViewParticipant.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        ArrayAdapter<Participant> participantAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_list_item_multiple_choice, mSetData.getArrayParticipants());

        listViewParticipant.setAdapter(participantAdapter);


        listViewParticipant.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (listParticipant.contains(mSetData.getArrayParticipants()[i])) {
                    listParticipant.remove(mSetData.getArrayParticipants()[i]);
                }
                else {
                    listParticipant.add(mSetData.getArrayParticipants()[i]);
                }

                mReunion.setParticipant(listParticipant);
            }
        });
    }
}