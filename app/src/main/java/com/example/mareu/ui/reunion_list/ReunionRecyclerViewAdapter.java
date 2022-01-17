package com.example.mareu.ui.reunion_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.R;
import com.example.mareu.event.DeleteReunionEvent;
import com.example.mareu.model.Reunion;

import org.greenrobot.eventbus.EventBus;

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

        holder.mReunionPlace.setText(doString(reunion.getPlace()));
        holder.mReunionTime.setText(doString(reunion.getStringTime()));
        holder.mReunionTopic.setText(reunion.getTopic());
        holder.mReunionParticipant.setText(doString(reunion.getParticipant()));
        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new DeleteReunionEvent(reunion));
            }
        });

    }

    public String doString(Object object) {
        if (object == null) {
            return "Aucune donnée";
        }
        else {
            return object.toString();
        }
    }

    @Override
    public int getItemCount() {
        return mReunions.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_list_delete_button)
        ImageButton mDeleteButton;
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
