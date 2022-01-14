package com.example.mareu.ui.reunion_list;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.mareu.model.Reunion;

public class AddReunionPageAdapter extends FragmentStateAdapter {

    private static Reunion reunion;
    private final int PAGE_NUMBER = 3;

    public AddReunionPageAdapter(@NonNull FragmentActivity fragmentActivity, Reunion reunion) {
        super(fragmentActivity);
        AddReunionPageAdapter.reunion = reunion;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new AddReunionFragmentPlace(reunion);
            case 1:
                return new AddReunionFragmentDate(reunion);
            case 2:
                return new AddReunionFragmentParticipant(reunion);
        }
        return null;
    }


    @Override
    public int getItemCount() {
        return PAGE_NUMBER;
    }

    public static Reunion getReunion() {
        return reunion;
    }
}
