package com.numberONE.maryfarm.Pick;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.numberONE.maryfarm.R;

import java.util.List;

public class PickAdapter extends RecyclerView.Adapter<PickViewHolder> {

    Context context;
    List<PickItem> items;

    public PickAdapter(Context context, List<PickItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public PickViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PickViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_pick_one,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PickViewHolder holder, int position) {
        holder.nameView.setText(items.get(position).getFlowerName());
        holder.imageView.setImageResource(items.get(position).getFlowerImg());

    }

    @Override
    public int getItemCount() { return items.size(); }
}
