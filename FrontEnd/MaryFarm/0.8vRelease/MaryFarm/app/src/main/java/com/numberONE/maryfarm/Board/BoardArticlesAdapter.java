package com.numberONE.maryfarm.Board;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.numberONE.maryfarm.R;

// 게시판의 게시글들 반환
public class BoardArticlesAdapter extends RecyclerView.Adapter<BoardArticlesAdapter.ViewHolder> {
    private String[] author, content;
    ViewHolder viewHolder;

    // 리사이클러 뷰로 들어갈 내용들 받는 부분
    public BoardArticlesAdapter(String[] author, String[] content){
        this.author = author;
        this.content = content;
    }
    // 커스텀 리스너 인터페이스 정의
    public interface OnArticleClickListener {
    // 클릭 시 동작할 함수
        void onPlantCheck(View v, int position) ;
        }

    // 리스너 객체 참조를 저장하는 변수
    private static OnArticleClickListener onArticleClickListener = null ;

    // OnItemClickListener 리스너 객체 참조를 어댑터에 전달하는 메서드
    public static void setOnArticleClickListener(OnArticleClickListener listener) {
        onArticleClickListener = listener;
    }
// 뷰홀더
    public static class ViewHolder extends  RecyclerView.ViewHolder{
        public TextView board_article_title;

        public ViewHolder(View view){
            super(view);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View holderView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_calendar_plant, parent, false);
        viewHolder = new ViewHolder(holderView);

        holderView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    onArticleClickListener.onPlantCheck(v, position);
                    Log.i(TAG, "onClick: ㅇㅇㅇㅇㅇㅇㅇㅇ");
                }
            }
        });

        return viewHolder;
    }
// ViewHolder viewType
    @Override
    public void onBindViewHolder(@NonNull ViewHolder mainHolder, int i) {
        mainHolder.board_article_title.setText(this.author[i]+"\n"+this.content[i]);
    }

    @Override
    public int getItemCount() {
        return author.length;
    }

}

