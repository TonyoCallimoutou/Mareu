package com.example.mareu.ui.reunion_list;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
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

        // List with "Add all"
        String[] participants = mSetData.getStringParticipants();

        ArrayAdapter<String> participantAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_list_item_multiple_choice, participants);

        listViewParticipant.setAdapter(participantAdapter);


        listViewParticipant.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==0) {
                    CheckedTextView v = (CheckedTextView) view;
                    if (v.isChecked()) {

                        for (int j=0; j< participants.length; j++) {
                            listViewParticipant.setItemChecked(j,true);
                        }

                        listParticipant = mApiService.getParticipant();
                    }
                    else {

                        for (int j=0; j< participants.length;j++) {
                            listViewParticipant.setItemChecked(j,false);
                        }

                        listParticipant.clear();
                    }
                }
                else {

                    listViewParticipant.setItemChecked(0,false);

                    if (listParticipant.contains(mSetData.getArrayParticipants()[i-1])) {
                        listParticipant.remove(mSetData.getArrayParticipants()[i-1]);
                    }
                    else {
                        listParticipant.add(mSetData.getArrayParticipants()[i-1]);
                    }

                }
                mReunion.setParticipant(listParticipant);
            }
        });
    }
}