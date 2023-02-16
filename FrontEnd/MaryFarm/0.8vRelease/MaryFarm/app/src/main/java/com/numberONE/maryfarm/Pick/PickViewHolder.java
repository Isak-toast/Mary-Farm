package com.numberONE.maryfarm.Pick;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.numberONE.maryfarm.R;

public class PickViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView nameView;

    public PickViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.pick_one_Img);
        nameView = itemView.findViewById(R.id.pick_one_Title);
    }
}
