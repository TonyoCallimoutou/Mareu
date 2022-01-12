package com.example.mareu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mareu.di.DI;
import com.example.mareu.methode.SetData;
import com.example.mareu.model.Participant;
import com.example.mareu.model.Reunion;
import com.example.mareu.service.ApiService;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddReunionFragmentParticipant#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddReunionFragmentParticipant extends Fragment {


    @BindView(R.id.list_view_participant)
    ListView listViewParticipant;


    ApiService mApiService;
    SetData mSetData;
    AddReunionFragmentPage1 fragmentPage1;
    AddReunionFragmentDate fragmentPageDate;

    Bundle bundle;
    private static final String REUNION = "REUNION";
    Reunion mReunion;
    List<Participant> listParticipant;

    public static AddReunionFragmentParticipant newInstance() {
        AddReunionFragmentParticipant fragment = new AddReunionFragmentParticipant();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mApiService = DI.getReunionApiService();
        mSetData = new SetData(mApiService);

        bundle = new Bundle();

        listParticipant = mApiService.getParticipantEmpty();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_reunionpart2, container, false);

        ButterKnife.bind(this,view);

        mReunion = (Reunion) getArguments().getSerializable(REUNION);
        if (mReunion.getParticipant() != null) {
            listParticipant = mReunion.getParticipant();
        }

        getListViewParticipant();

        return view;
    }

    public void getListViewParticipant() {


        listViewParticipant.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        ArrayAdapter<Participant> participantAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_list_item_multiple_choice, mSetData.getParticipants());

        listViewParticipant.setAdapter(participantAdapter);

        for(int i = 0; i< mSetData.getParticipants().length; i++ )  {
            if (listParticipant.contains(mSetData.getParticipants()[i])) {
                listViewParticipant.setItemChecked(i,true);
            }
        }


        listViewParticipant.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (listParticipant.contains(mSetData.getParticipants()[i])) {
                    listParticipant.remove(mSetData.getParticipants()[i]);
                }
                else {
                    listParticipant.add(mSetData.getParticipants()[i]);
                }
            }
        });

    }

    @OnClick(R.id.page1)
    void goToPage1() {
        mReunion.setParticipant(listParticipant);

        fragmentPage1 = new AddReunionFragmentPage1().newInstance();
        bundle.putSerializable(REUNION,(Serializable) mReunion);
        fragmentPage1.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_add_fragment,fragmentPage1)
                .commit();
    }

    @OnClick(R.id.page2)
    void goToPage2() {
        mReunion.setParticipant(listParticipant);

        fragmentPageDate = new AddReunionFragmentDate().newInstance();
        bundle.putSerializable(REUNION,(Serializable) mReunion);
        fragmentPageDate.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_add_fragment,fragmentPageDate)
                .commit();
    }

    @OnClick(R.id.create)
    void addReunion() {
        mReunion.setParticipant(listParticipant);
        mApiService.createReunion(mReunion);
        getActivity().finish();
    }

}