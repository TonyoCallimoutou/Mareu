package com.example.mareu.ui.reunion_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Spinner;

import com.example.mareu.R;
import com.example.mareu.di.DI;
import com.example.mareu.methode.SetSpinner;
import com.example.mareu.model.Place;
import com.example.mareu.model.Reunion;
import com.example.mareu.service.ApiService;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.sql.Time;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddReunionActivity extends AppCompatActivity {

    @BindView(R.id.spinner_place)
    Spinner mSpinnerPlace;
    @BindView(R.id.input_topic)
    TextInputLayout mInputTopic;

    ApiService mApiService;
    SetSpinner setSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reunion);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mApiService = DI.getReunionApiService();

        setSpinner = new SetSpinner(mApiService);

        setSpinner.getSpinnerPlaces(this, mSpinnerPlace);


    }

    @OnClick(R.id.alertDialog)
    void alertDialog() {
        setSpinner.getDialogParticipant(this,mApiService);
    }


    @OnClick(R.id.create)
    void addReunion() {

        // Data
        Place place = (Place) mSpinnerPlace.getSelectedItem();


        // Reunion
        Reunion reunion = new Reunion(
                System.currentTimeMillis(),
                null,
                place,
                mInputTopic.getEditText().getText().toString(),
                null
                );

        mApiService.createReunion(reunion);
        finish();
    }



    /**
     * Used to navigate to this activity
     * @param activity
     */
    public static void navigate(FragmentActivity activity) {
        Intent intent = new Intent(activity, AddReunionActivity.class);
        ActivityCompat.startActivity(activity, intent, null);
    }

}