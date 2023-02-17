package com.numberONE.maryfarm.Diary;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.numberONE.maryfarm.Retrofit.Comments;
import com.numberONE.maryfarm.R;
import com.numberONE.maryfarm.Retrofit.dto.DetailDiariesPerPlantView.DetailDiaryCommentDTO;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    Context context;
    List<DetailDiaryCommentDTO> items ;
    int likeCnt;

    public CommentAdapter(Context context, List<DetailDiaryCommentDTO> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public CommentAdapter.CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CommentViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_comment,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        holder.nickname.setText((items.get(position).getUserName()));
        holder.content.setText((items.get(position).getContent()));
        holder.commentLike.setText(("" + items.get(position).getLikes()));
        Glide.with(context)
                .load(items.get(position).getProfilePath())
                .into(holder.profileImg);

        likeCnt = items.get(position).getLikes();

        holder.emptyHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.emptyHeart.setVisibility(View.GONE);
                holder.fullHeart.setVisibility(View.VISIBLE);
                likeCnt++;
                holder.commentLike.setText(("" + likeCnt));
            }
        });

        holder.fullHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.emptyHeart.setVisibility(View.VISIBLE);
                holder.fullHeart.setVisibility(View.GONE);
                likeCnt--;
                holder.commentLike.setText(("" + likeCnt));
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private static AdapterView.OnItemClickListener onItemClickListener = null;

    public static void setOnItemClickListener(AdapterView.OnItemClickListener listener){
        onItemClickListener = listener;
    }

    // viewholder 작성
    public static class CommentViewHolder extends RecyclerView.ViewHolder{

        TextView nickname, commentDate, content, commentLike;
        ImageView profileImg, emptyHeart, fullHeart;
        Button.OnClickListener clickListener;

        public CommentViewHolder(@NonNull View itemView){
            super(itemView);

            nickname = itemView.findViewById(R.id.commentNickname);
            commentDate = itemView.findViewById(R.id.commentDate);
            content = itemView.findViewById(R.id.commentContent);
            commentLike = itemView.findViewById(R.id.commentLike);
            profileImg = itemView.findViewById(R.id.commentProfileImg);
            emptyHeart = itemView.findViewById(R.id.emptyIcon);
            fullHeart = itemView.findViewById(R.id.fullIcon);
        }
    }

}
