package com.numberONE.maryfarm.ui.home.FarmFeed;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.numberONE.maryfarm.R;
import com.numberONE.maryfarm.Retrofit.Diary.UserItem;
import com.numberONE.maryfarm.Retrofit.dto.FirstHomeView.HomeFollowerImageDTO;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


// 상단 팔로워 피드 리사이클러뷰 adapter
public class FeedFollowersAdapter extends RecyclerView.Adapter<FeedFollowersAdapter.ViewHolder> {

    private List<HomeFollowerImageDTO> items ;
    private Context context;

    ViewHolder viewHolder;

    public FeedFollowersAdapter(List<HomeFollowerImageDTO> items, Context context) {
        this.items = items;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView image;
        public CircleImageView profile;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.image= itemView.findViewById(R.id.follower_image);
            this.profile=itemView.findViewById(R.id.follower_profile);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_feed_followers,parent,false);

        viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            Glide.with(context)
                    .load(items.get(position).getLatestDiaryImagePath())
                    .skipMemoryCache(true)
                    .error(R.drawable.full_heart_icon)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(holder.image);

            Glide.with(context)
                    .load(items.get(position).getProfilePath())
                    .skipMemoryCache(true)
                    .error(R.drawable.full_heart_icon)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(holder.profile);

            holder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            holder.profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return items!=null ? items.size() : 0 ;
     }



}

