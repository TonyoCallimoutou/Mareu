package com.example.mareu.ui.reunion_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.R;
import com.example.mareu.model.Place;
import com.example.mareu.model.Reunion;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReunionRecyclerViewAdapter extends RecyclerView.Adapter<ReunionRecyclerViewAdapter.ViewHolder> {

    private final List<Reunion> mReunions;

    public ReunionRecyclerViewAdapter(List<Reunion> reunions) {
        mReunions = reunions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_reunion_item, null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Reunion reunion =mReunions.get(position);

        holder.mReunionPlace.setText(reunion.getPlace().toString());
        holder.mReunionTime.setText(reunion.getTime().toString());
        holder.mReunionTopic.setText(reunion.getTopic());
        holder.mReunionParticipant.setText(reunion.getParticipant().toString());

    }

    @Override
    public int getItemCount() {
        return mReunions.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_list_delete_button)
        Button mDeleteButton;
        @BindView(R.id.item_list_place)
        TextView mReunionPlace;
        @BindView(R.id.item_list_time)
        TextView mReunionTime;
        @BindView(R.id.item_list_topic)
        TextView mReunionTopic;
        @BindView(R.id.item_list_participant)
        TextView mReunionParticipant;

        public ViewHolder (View view) {
            super(view);
            ButterKnife.bind(this,view);
        }

    }

}
