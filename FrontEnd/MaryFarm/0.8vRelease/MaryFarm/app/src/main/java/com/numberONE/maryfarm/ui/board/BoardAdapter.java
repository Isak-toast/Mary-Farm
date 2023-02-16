package com.numberONE.maryfarm.ui.board;

import android.content.SharedPreferences;
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

import java.util.ArrayList;
import java.util.List;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {
    private static final String TAG = " BoardAdapter";
    static List<BoardArticle> article=new ArrayList<>();


    ViewHolder viewHolder;

//    ------ 어댑터 밖으로 리스너를 빼서 활용하기 위한 로직 -----------

    //외부에서 구현해서 사용할 인터페이스 정의
    public interface OnItemClickListner{
        void onItemClick(View v ,int position,String articleId);
    }

    //리스너 객체 참조를 저장하는 변수
    private static OnItemClickListner listener = null;

    //OnItemClickListner 리스너 객체 참조를 어댑터에 전달하는 메서드
    public void setOnItemClickListener(OnItemClickListner listener){
        this.listener=listener;
    }

//    ------ 어댑터 밖으로 리스너를 빼서 활용하기 위한 로직 끝 -----------

    public BoardAdapter(){
        List<BoardArticle> list =new ArrayList<>();
        this.article=list;
    }

    public BoardAdapter(List<BoardArticle> article){
        this.article=article;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public RelativeLayout layout;
        public TextView title, userName, date, viewCnt, commentCnt;
//        public ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.board_item_title);
            this.userName = itemView.findViewById(R.id.board_item_nickname);
            this.date = itemView.findViewById(R.id.board_item_date);
            this.viewCnt = itemView.findViewById(R.id.board_item_viewCnt);
            this.commentCnt = itemView.findViewById(R.id.board_item_commentCnt);
            this.layout = itemView.findViewById(R.id.recycler_board_layout);
//            this.image=itemView.findViewById(R.id.board_item_image);


//       sharedpreferences 는 어댑터에서 사용하려고 하면 로직이 복잡해져서 외부로 빼서 사용하기 위해
//            커스텀 리스너 만든 후 외부에서 꺼내서 사용
//           리사이클러뷰에서 아이템 하나 클릭 시 리스너에 담아주기
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if(pos!=RecyclerView.NO_POSITION){
                        if(listener!=null){
                            listener.onItemClick(view,pos,article.get(pos).getArticleId());
                        }
                    }
                }
            });
//    --------     클릭리스너 로직 끝  -------------
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
            holder.userName.setText(article.get(position).getUserName());
            holder.date.setText(article.get(position).getCreatedDate());
            holder.viewCnt.setText(article.get(position).getViews());
//            holder.commentCnt.setText(article.get(position).getCommentCnt()); // 댓글 받을 수 있는 지 체크 후 댓글의 length 넣어주기
//            viewHolder.image.setImageResource(article.get.); // 이미지 넣을거면 api 수정 요청하기

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return article !=null ? article.size() : 0 ;
    }
}
