package com.numberONE.maryfarm.ui.board;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.numberONE.maryfarm.R;
import com.numberONE.maryfarm.Retrofit.Board.BoardArticle;

import java.util.List;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

    List<BoardArticle> article;

    ViewHolder viewHolder;

    public BoardAdapter(List<BoardArticle> article){
        this.article=article;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public RelativeLayout layout;
        public TextView title,nickname,date,viewCnt,commentCnt;
        public ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title=itemView.findViewById(R.id.board_item_title);
            this.nickname=itemView.findViewById(R.id.board_item_nickname);
            this.date=itemView.findViewById(R.id.board_item_date);
            this.viewCnt=itemView.findViewById(R.id.board_item_viewCnt);
            this.commentCnt=itemView.findViewById(R.id.board_item_commentCnt);
            this.layout=itemView.findViewById(R.id.recycler_board_layout);
//            this.image=itemView.findViewById(R.id.board_item_image);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_board,parent,false);

        viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {



            holder.title.setText(article.get(position).getTitle());
            holder.nickname.setText(article.get(position).getUserName());
            holder.date.setText(article.get(position).getCreatedDate());
            holder.viewCnt.setText(article.get(position).getViews());
            holder.commentCnt.setText(article.get(position).getCommentCnt());
//            viewHolder.image.setImageResource(article.get); // 이미지 넣을거면 api 수정 요청하기

            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = holder.getAdapterPosition();

                    Context context =view.getContext();

                    Intent intent ;//  리사이클러 뷰 아이템 하나 클릭시 상세 페이지로 이동

                }
            });



        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return article !=null ? article.size() : 0 ;
    }
}
