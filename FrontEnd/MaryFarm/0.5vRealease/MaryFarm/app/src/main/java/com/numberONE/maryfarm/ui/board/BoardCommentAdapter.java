package com.numberONE.maryfarm.ui.board;

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
import com.numberONE.maryfarm.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class BoardCommentAdapter extends RecyclerView.Adapter<BoardCommentAdapter.ViewHolder> {

    private String[] nickname,date,like,content;
    private int[] profile,image;

    ViewHolder viewHolder;

    public BoardCommentAdapter(){}
    public BoardCommentAdapter(String[] nickname,String[] date,String[] like,String[] content, int[] profile, int[] image) {
        this.nickname=nickname;
        this.date=date;
        this.like=like;
        this.content=content;
        this.profile=profile;
        this.image=image;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView nickname,date,like,content;
        public CircleImageView profile;
        public ImageView empty;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.nickname=itemView.findViewById(R.id.board_recycler_nickname);
            this.date=itemView.findViewById(R.id.board_recycler_date);
            this.like=itemView.findViewById(R.id.board_recycler_comment_like);
            this.content=itemView.findViewById(R.id.board_recycler_content);
            this.profile=itemView.findViewById(R.id.board_recycler_profile);
            this.empty=itemView.findViewById(R.id.board_recycler_emptyIcon);
        }
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_board_comment,parent,false);

        viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            viewHolder.nickname.setText(this.nickname[position]);
            viewHolder.date.setText(this.date[position]);
            viewHolder.like.setText(this.like[position]);
            viewHolder.content.setText(this.content[position]);
            viewHolder.profile.setImageResource(this.profile[position]);
            viewHolder.empty.setImageResource(this.image[position]);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return nickname.length;
    }

}
