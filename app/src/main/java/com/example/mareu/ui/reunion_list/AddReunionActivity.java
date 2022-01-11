package com.example.mareu.ui.reunion_list;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.mareu.AddReunionFragmentPart1;
import com.example.mareu.AddReunionFragmentPart2;
import com.example.mareu.R;
import com.example.mareu.di.DI;
import com.example.mareu.model.Participant;
import com.example.mareu.model.Place;
import com.example.mareu.model.Reunion;
import com.example.mareu.service.ApiService;

import java.io.Serializable;
import java.lang.reflect.Parameter;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddReunionActivity extends AppCompatActivity {

    ApiService mApiService;
    AddReunionFragmentPart1 fragmentPage1;

    Bundle bundle;
    private static final String REUNION = "REUNION";
    private Reunion mReunion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reunion);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mApiService = DI.getReunionApiService();

        bundle = new Bundle();

        mReunion = new Reunion(
                System.currentTimeMillis(),
                null,
                null,
                null,
                null);

        initFragment();

    }

    /**
     * Used to navigate to this activity
     * @param activity
     */
    public static void navigate(FragmentActivity activity) {
        Intent intent = new Intent(activity, AddReunionActivity.class);
        ActivityCompat.startActivity(activity, intent, null);
    }

    public void initFragment() {

        fragmentPage1 = new AddReunionFragmentPart1().newInstance();
        bundle.putSerializable(REUNION,(Serializable) mReunion);
        fragmentPage1.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_add_fragment,fragmentPage1)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home : {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }


}