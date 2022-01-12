package com.example.mareu.ui.reunion_list;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.mareu.AddReunionFragmentPage1;
import com.example.mareu.R;
import com.example.mareu.di.DI;
import com.example.mareu.model.Reunion;
import com.example.mareu.service.ApiService;

import java.io.Serializable;

import butterknife.ButterKnife;

public class AddReunionActivity extends AppCompatActivity {

    ApiService mApiService;
    AddReunionFragmentPage1 fragmentPage1;

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

        fragmentPage1 = new AddReunionFragmentPage1().newInstance();
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