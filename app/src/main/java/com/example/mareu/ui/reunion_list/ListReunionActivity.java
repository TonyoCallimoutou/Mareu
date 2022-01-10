package com.example.mareu.ui.reunion_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.Spinner;

import com.example.mareu.R;
import com.example.mareu.di.DI;
import com.example.mareu.methode.SetSpinner;
import com.example.mareu.service.ApiService;
import com.example.mareu.ui.reunion_list.AddReunionActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListReunionActivity extends AppCompatActivity{

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.spinner_filter)
    Spinner mSpinnerFilter;


    ApiService mApiService;
    ReunionFragment reunionFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reunion_list);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        mApiService = DI.getReunionApiService();

        SetSpinner setSpinner = new SetSpinner(mApiService);

        setSpinner.getFilter(this,mSpinnerFilter);

        initFragment();

    }

    public void initFragment() {
        reunionFragment = new ReunionFragment().newInstance(0,null);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, reunionFragment)
                .commit();
    }


    @OnClick(R.id.fab_add_reunion)
    void addReunion () {
        AddReunionActivity.navigate(this);
    }
}