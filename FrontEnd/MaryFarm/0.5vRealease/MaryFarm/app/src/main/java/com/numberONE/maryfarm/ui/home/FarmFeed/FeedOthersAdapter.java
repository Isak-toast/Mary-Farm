package com.numberONE.maryfarm.ui.home.FarmFeed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.numberONE.maryfarm.R;
import com.numberONE.maryfarm.Retrofit.Diary.DiaryTopRecommend;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FeedOthersAdapter extends RecyclerView.Adapter<FeedOthersAdapter.ViewHolder> {

    ViewHolder viewHolder;

    Context context;
    List<DiaryTopRecommend> diaryTopRecommendList;

    // 생성자
    public FeedOthersAdapter(Context context, List<DiaryTopRecommend> list){
        this.context=context;
        this.diaryTopRecommendList=list;
    }


    public static class ViewHolder extends  RecyclerView.ViewHolder{
        public TextView nickname, title,time;
        public ImageView image1,image2,image3;
        public CircleImageView profile;

        public ViewHolder(View view){
            super(view);
            this.nickname = view.findViewById(R.id.other_nickname);
            this.title = view.findViewById(R.id.other_title);
            this.image1=view.findViewById(R.id.other_image_1);
            this.image2=view.findViewById(R.id.other_image_2);
            this.image3=view.findViewById(R.id.other_image_3);
            this.profile=view.findViewById(R.id.other_profile);
            this.time=view.findViewById(R.id.other_time);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View holderView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_feed_others, parent, false);
        viewHolder = new ViewHolder(holderView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder mainHolder, int i) {
//     리스트로 받아와서 이미지 처리로직 다시 정리하기
        //      이미지 1 ,2 ,3
//        Glide.with(context)
//                .load(diaryTopRecommendList.get(i).getImagepath())
//                .skipMemoryCache(true)
//                .error(R.drawable.full_heart_icon)
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .into(mainHolder.image1);
//        Glide.with(context)
//                .load(diaryTopRecommendList.get(i).getImagepath())
//                .skipMemoryCache(true)
//                .error(R.drawable.full_heart_icon)
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .into(mainHolder.image2);
//        Glide.with(context)
//                .load(diaryTopRecommendList.get(i).getImagepath())
//                .skipMemoryCache(true)
//                .error(R.drawable.full_heart_icon)
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .into(mainHolder.image3);

//      프로필 이미지
//        Glide.with(context)
//                .load(diaryTopRecommendList.get(i).getPlant().getUserdata().getProfilepath())
//                .skipMemoryCache(true)
//                .error(R.drawable.full_heart_icon)
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .into(mainHolder.profile);

//        mainHolder.title.setText(diaryTopRecommendList.get(i).getPlant().getTitle());
//        mainHolder.nickname.setText(diaryTopRecommendList.get(i).getPlant().getUserdata().getNickname());

//         시간 차이 로직 구현하기
//        mainHolder.time.setText(diaryTopRecommendList.get(i).getPlant().getDate());

    }

    @Override
    public int getItemCount() {
        return diaryTopRecommendList!=null ? diaryTopRecommendList.size() : 0 ;
    }

}

