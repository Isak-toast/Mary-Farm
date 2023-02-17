package com.numberONE.maryfarm.ui.myfarm;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.numberONE.maryfarm.Diary.DiaryDetailActivity;
import com.numberONE.maryfarm.R;
import com.numberONE.maryfarm.Retrofit.Thumbnail;
import com.numberONE.maryfarm.Retrofit.dto.DetailDiariesPerPlantView.DetailDiariesPerPlantDTO;

import java.util.List;

public class MyfarmAdapter extends RecyclerView.Adapter<MyfarmAdapter.MyViewHolder>{

    Context context;
    List<Thumbnail> items;

    public MyfarmAdapter(Context context, List<Thumbnail> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyfarmAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_myfarm,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText((items.get(position).getTitle()));
        Glide.with(context)
                .load(items.get(position).getThumbImg1())
                .into(holder.first_photo);
        Glide.with(context)
                .load(items.get(position).getThumbImg2())
                .into(holder.second_photo);
        Glide.with(context)
                .load(items.get(position).getThumbImg3())
                .into(holder.third_photo);
        holder.thumbnailStartDate.setText(items.get(position).getPlantCreatedDate().substring(0,10));
        if (items.get(position).getHarvestDate() != null) {
            holder.thumbnailEndDate.setText(items.get(position).getHarvestDate().substring(0,10));
        } else {
            holder.thumbnailEndDate.setText("ing");
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DiaryDetailActivity.class); //fragment라서 activity intent와는 다른 방식
                DetailDiariesPerPlantDTO detailDiariesPerPlantDTO = (DetailDiariesPerPlantDTO) items.get(holder.getAdapterPosition()).getDetailDiariesPerPlantDTO();
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("detailDiariesPerPlantDTO", detailDiariesPerPlantDTO);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // viewholder 작성
    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title, thumbnailStartDate, thumbnailEndDate;
        ImageView first_photo, second_photo, third_photo;


        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            title = itemView.findViewById(R.id.title);
            first_photo = itemView.findViewById(R.id.first_photo);
            second_photo = itemView.findViewById(R.id.second_photo);
            third_photo = itemView.findViewById(R.id.third_photo);
            thumbnailStartDate = itemView.findViewById(R.id.thunbnailStartDate);
            thumbnailEndDate = itemView.findViewById(R.id.thunbnailEndDate);
        }
    }
}
