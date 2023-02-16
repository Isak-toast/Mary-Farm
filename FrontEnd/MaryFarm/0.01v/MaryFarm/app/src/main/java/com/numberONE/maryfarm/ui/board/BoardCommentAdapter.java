package com.numberONE.maryfarm.ui.board;

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
import com.numberONE.maryfarm.R;
import com.numberONE.maryfarm.Retrofit.Board.BoardComments;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class BoardCommentAdapter extends RecyclerView.Adapter<BoardCommentAdapter.ViewHolder> {

    static List<BoardComments> boardComments;
    ViewHolder viewHolder;
    int view_cnt;

    public BoardCommentAdapter(List<BoardComments> list) {
        this.boardComments=list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView nickname,like,content;
        public CircleImageView profile;
        public ImageView empty,full;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.nickname=itemView.findViewById(R.id.board_recycler_nickname);
            this.like=itemView.findViewById(R.id.board_recycler_comment_like);
            this.content=itemView.findViewById(R.id.board_recycler_content);
            this.profile=itemView.findViewById(R.id.board_recycler_profile);
            this.empty=itemView.findViewById(R.id.board_recycler_emptyIcon);
            this.full=itemView.findViewById(R.id.board_recycler_fullIcon);
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
            view_cnt =boardComments.get(position).getLikes();
            holder.nickname.setText(boardComments.get(position).getUserName());
            holder.like.setText(boardComments.get(position).getLikes()+"");
            holder.content.setText(view_cnt+"");
            Glide.with(holder.profile).load(boardComments.get(position).getProfile());
            Glide.with(holder.empty).load(boardComments.get(position).getProfile());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return boardComments!=null ? boardComments.size() : 0 ;
    }

}
