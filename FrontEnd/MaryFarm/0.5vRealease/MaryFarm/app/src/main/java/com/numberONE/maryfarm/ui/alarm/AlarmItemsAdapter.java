package com.numberONE.maryfarm.ui.alarm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.numberONE.maryfarm.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class AlarmItemsAdapter extends RecyclerView.Adapter<AlarmItemsAdapter.ViewHolder> {
    private String[] nickname, content, date;
    private int[] profile;
    ViewHolder viewHolder;

    public AlarmItemsAdapter(String[] nickname, String[] content, String[] date, int[] profile){
        this.nickname = nickname;
        this.content = content;
        this.profile = profile;
        this.date = date;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView nickname, content, date;
        public CircleImageView profile;

        public ViewHolder(View view){
            super(view);
            this.nickname = view.findViewById(R.id.alarmNickname);
            this.content = view.findViewById(R.id.alarmContent);
            this.profile = view.findViewById(R.id.alarmProfile);
            this.date = view.findViewById(R.id.alarmDate);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View holderView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_alarms, parent, false);
        viewHolder = new ViewHolder(holderView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder mainHolder, int i) {
        mainHolder.nickname.setText(this.nickname[i]);
        mainHolder.content.setText(this.content[i]);
        mainHolder.date.setText(this.date[i]);
        mainHolder.profile.setImageResource(this.profile[i]);
    }

    @Override
    public int getItemCount() { return nickname.length;}

}
