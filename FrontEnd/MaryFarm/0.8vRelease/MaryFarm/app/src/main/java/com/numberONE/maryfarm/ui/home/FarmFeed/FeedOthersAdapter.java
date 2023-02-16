package com.numberONE.maryfarm.ui.home.FarmFeed;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.numberONE.maryfarm.Diary.DiaryDetailActivity;
import com.numberONE.maryfarm.R;
import com.numberONE.maryfarm.Retrofit.Diary.DiaryTopRecommend;
import com.numberONE.maryfarm.Retrofit.ServerAPI;
import com.numberONE.maryfarm.Retrofit.Thumbnail;
import com.numberONE.maryfarm.Retrofit.dto.DetailDiariesPerPlantView.DetailDiariesPerPlantDTO;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FeedOthersAdapter extends RecyclerView.Adapter<FeedOthersAdapter.ViewHolder> {
    ViewHolder viewHolder;

    Context context;
    List<Thumbnail> diaryTopRecommendList;

    private DetailDiariesPerPlantDTO detailDiariesPerPlantDTO;

    // 생성자
    public FeedOthersAdapter(Context context, List<Thumbnail> list){
        this.context=context;
        this.diaryTopRecommendList=list;
    }


    public static class ViewHolder extends  RecyclerView.ViewHolder{
        public TextView title, created_at, harvest_at;
        public ImageView image1,image2,image3, profile;

        public ViewHolder(View view){
            super(view);
            this.title = view.findViewById(R.id.other_title);
            this.image1=view.findViewById(R.id.other_image_1);
            this.image2=view.findViewById(R.id.other_image_2);
            this.image3=view.findViewById(R.id.other_image_3);
            this.profile=view.findViewById(R.id.other_profile);
            this.created_at=view.findViewById(R.id.thunbnailStartDate);
            this.harvest_at=view.findViewById(R.id.thunbnailEndDate);
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
        String plantId = diaryTopRecommendList.get(i).getPlantId();

        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl("https://985e5bce-3b72-4068-8079-d7591e5374c9.mock.pstmn.io/api/")
                //.baseUrl("https://maryfarm.shop/maryfarm-plant-service/api/")
                //.baseUrl("http://192.168.31.244:8000/maryfarm-plant-service/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServerAPI serverAPI2 = retrofit2.create(ServerAPI.class);
        Call<DetailDiariesPerPlantDTO> call2 = serverAPI2.getDiaries(plantId);
        call2.enqueue(new Callback<DetailDiariesPerPlantDTO>() {
            @Override
            public void onResponse(Call<DetailDiariesPerPlantDTO> call, Response<DetailDiariesPerPlantDTO> response) {
                if (response.body() != null) {
                    detailDiariesPerPlantDTO = response.body();
                }
            }
            @Override
            public void onFailure(Call<DetailDiariesPerPlantDTO> call, Throwable t) {
                Log.d("", "onFailure: "+t.toString());
            }
        });

        //      이미지 1 ,2 ,3
        Glide.with(context)
                .load(diaryTopRecommendList.get(i).getThumbImg1())
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(mainHolder.image1);
        Glide.with(context)
                .load(diaryTopRecommendList.get(i).getThumbImg2())
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(mainHolder.image2);
        Glide.with(context)
                .load(diaryTopRecommendList.get(i).getThumbImg3())
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(mainHolder.image3);
        Glide.with(context)
                .load(diaryTopRecommendList.get(i).getProfilepath())
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(mainHolder.profile);

        mainHolder.title.setText(diaryTopRecommendList.get(i).getTitle());
        mainHolder.created_at.setText(diaryTopRecommendList.get(i).getPlantCreatedDate().substring(0,10));

        if (diaryTopRecommendList.get(i).getHarvestDate() == null) {
            mainHolder.harvest_at.setText("ing");
        } else {
            mainHolder.harvest_at.setText(diaryTopRecommendList.get(i).getHarvestDate().substring(0,10));
        }

        mainHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DiaryDetailActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("detailDiariesPerPlantDTO", detailDiariesPerPlantDTO);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return diaryTopRecommendList!=null ? diaryTopRecommendList.size() : 0 ;
    }

}

