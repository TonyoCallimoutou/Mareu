package com.example.mareu.ui.reunion_list;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mareu.R;
import com.example.mareu.ui.reunion_list.AddReunionActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListReunionActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reunion_list);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.fab_add_reunion)
    void addReunion () {
        AddReunionActivity.navigate(this);
    }
}