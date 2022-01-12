package com.example.mareu.ui.reunion_list;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mareu.R;
import com.example.mareu.di.DI;
import com.example.mareu.event.DeleteReunionEvent;
import com.example.mareu.model.Place;
import com.example.mareu.model.Reunion;
import com.example.mareu.service.ApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReunionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReunionFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private List<Reunion> mReunion;
    private ApiService mApiService;



    private static final String FILTER_POSITION = "FILTER_POSITION";
    private static final String OBJECT = "OBJECT";


    private int mFilterPosition;
    private Object mObject;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param filterPosition Parameter 1.
     * @param object Parameter 2.
     * @return A new instance of fragment ReunionFragment.
     */

    public static ReunionFragment newInstance(int filterPosition, Object object) {
        ReunionFragment fragment = new ReunionFragment();
        Bundle args = new Bundle();
        args.putInt(FILTER_POSITION, filterPosition);
        args.putSerializable(OBJECT, (Serializable) object);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getReunionApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reunion, container, false);
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        return view;
    }

    /**
     * Init the List of reunion
     */
    public void initList() {

        mFilterPosition = getArguments().getInt(FILTER_POSITION);
        mObject = getArguments().getSerializable(OBJECT);

        if (mFilterPosition == 0) {
            mReunion = mApiService.getReunion();
        }

        else if (mFilterPosition == 1) {
            Date object = (Date) mObject;
            mReunion = mApiService.getReunionsByTime(object);
        }

        else if (mFilterPosition == 2) {
            Place object = (Place) mObject;
            mReunion = mApiService.getReunionsByPlace(object);
        }

        mRecyclerView.setAdapter(new ReunionRecyclerViewAdapter(mReunion));
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onDeleteReunionEvent(DeleteReunionEvent event) {
        mApiService.deleteReunion(event.getReunion());
        initList();
    }
}