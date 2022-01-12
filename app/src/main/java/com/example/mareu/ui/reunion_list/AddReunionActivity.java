package com.example.mareu.ui.reunion_list;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.mareu.AddReunionFragmentPage1;
import com.example.mareu.R;
import com.example.mareu.di.DI;
import com.example.mareu.model.Reunion;
import com.example.mareu.service.ApiService;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddReunionActivity extends AppCompatActivity {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.container_add_fragment)
    ViewPager2 viewPager;
    @BindView(R.id.text_create_reunion)
    TextView reunionCreate;

    ApiService mApiService;

    private Reunion mReunion;
    AddReunionPageAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reunion);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mApiService = DI.getReunionApiService();

        mReunion = new Reunion(
                System.currentTimeMillis(),
                null,
                null,
                null,
                null);

        adapter = new AddReunionPageAdapter(this,mReunion);
        viewPager.setAdapter(adapter);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText("OBJECT " + (position + 1))
        ).attach();


    }

    /**
     * Used to navigate to this activity
     * @param activity
     */
    public static void navigate(FragmentActivity activity) {
        Intent intent = new Intent(activity, AddReunionActivity.class);
        ActivityCompat.startActivity(activity, intent, null);
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

    @OnClick(R.id.create)
    void createReunion() {
        /*
        mReunion = AddReunionPageAdapter.getReunion();
        String s = "Reunion "+ mReunion.getTime().toString() ;
        reunionCreate.setText(s);

         */

        mApiService.createReunion(mReunion);
        finish();
    }


}