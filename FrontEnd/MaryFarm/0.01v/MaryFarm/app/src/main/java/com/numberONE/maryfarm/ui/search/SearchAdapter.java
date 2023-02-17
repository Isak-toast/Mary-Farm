package com.numberONE.maryfarm.ui.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.numberONE.maryfarm.R;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private String[] title,nickname;
    private int[] image,image1,image2,profile;

    ViewHolder viewHolder;

    public SearchAdapter(int[] image,int[] image1,int[] image2,int[] profile,
                         String[] nickname,  String[] title){
        this.image=image;
        this.image1=image1;
        this.image2=image2;
        this.profile=profile;
        this.nickname=nickname;
        this.title=title;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView title,nickname;
        public ImageView image,image1,image2,profile;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.image=itemView.findViewById(R.id.search_image_1);
            this.image1=itemView.findViewById(R.id.search_image_2);
            this.image2=itemView.findViewById(R.id.search_image_3);
            this.profile=itemView.findViewById(R.id.search_profile);
            this.nickname=itemView.findViewById(R.id.search_nickname);
            this.title=itemView.findViewById(R.id.search_title);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_search,parent,false);

        viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            viewHolder.image.setImageResource(this.image[position]);
            viewHolder.image1.setImageResource(this.image1[position]);
            viewHolder.image2.setImageResource(this.image2[position]);
            viewHolder.profile.setImageResource(this.profile[position]);
            viewHolder.nickname.setText(this.nickname[position]);
            viewHolder.title.setText(this.title[position]);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return title.length;
    }
}
