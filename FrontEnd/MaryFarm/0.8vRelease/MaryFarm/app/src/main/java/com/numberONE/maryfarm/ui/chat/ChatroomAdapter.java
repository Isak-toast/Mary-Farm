package com.numberONE.maryfarm.ui.chat;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.numberONE.maryfarm.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

// 달력 진입 시 키우고 있는 작물 리스트 체크박스 반환~
public class ChatroomAdapter extends RecyclerView.Adapter<ChatroomAdapter.ViewHolder> {
    //    private String[] nickname, date;
//    private String[][] content;
    private int roomId;
    //    private int[] profile, tier;
    String[] nickname = {"왕감자", "블루베리맘", "당근전문가", "성주꿀참외"};
    String[][] content = {{"영양제 뭐 쓰세요?"}, {"주무세요..?"}, {"제가 당근이 풍년이라 나눠드릴게요~", "네! 당근이랑 좀 교환해요"}, {"우리 힘내자", "이 편지는 영국에서 시작하여 행운을 뿌리는 중"}};
    String[][] date = {{"9:02am"}, {"3:45am"}, {"1일 전", "1일 전"}, {"5일 전", "4일 전"}};
    int[] profile = {R.drawable.profilebaek,R.drawable.profilekim,R.drawable.profilejang,R.drawable.profilekang};
    ViewHolder viewHolder;

    // 리사이클러 뷰로 들어갈 내용들 받는 부분
    public ChatroomAdapter(String roomId){
        this.roomId = Integer.parseInt(roomId);
    }

    // 커스텀 리스너 인터페이스 정의
    public interface OnChatClickListener {
        // 클릭 시 동작할 함수
        void onChatClick(View v, int position);
    }

    // 리스너 객체 참조를 저장하는 변수
    static OnChatClickListener onChatClickListener = null;
    public static void setOnChatClickListener(OnChatClickListener listener) {
        onChatClickListener = listener;
    }

    // 뷰홀더
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public RelativeLayout leftchat, rightchat;
        public CircleImageView chatoppoProfileImg, chatmyProfileImg;
        public TextView chatopponame, chatoppoContent, chatoppoDate, chatmyContent, chatmyDate;

        //    Date date = new Date(now);
//    SimpleDateFormat sdf = new SimpleDateFormat(“yyyy-MM-dd”);
//    String getTime = sdf.format(date);
        public ViewHolder(View view){
            super(view);
            leftchat = view.findViewById(R.id.leftchat);
            rightchat = view.findViewById(R.id.rightchat);
            chatoppoProfileImg = view.findViewById(R.id.chatoppoProfileImg);
            chatmyProfileImg = view.findViewById(R.id.chatmyProfileImg);
            chatopponame = view.findViewById(R.id.chatopponame);
            chatoppoContent = view.findViewById(R.id.chatoppoContent);
            chatoppoDate = view.findViewById(R.id.chatoppoDate);
            chatmyContent = view.findViewById(R.id.chatmyContent);
            chatmyDate = view.findViewById(R.id.chatmyDate);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View holderView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_chatroom, parent, false);
        viewHolder = new ViewHolder(holderView);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder mainHolder, int i) {
//        leftchat = view.findViewById(R.id.leftchat);
//        rightchat = view.findViewById(R.id.rightchat);
//        chatoppoProfileImg = view.findViewById(R.id.chatoppoProfileImg);
//        chatmyContent = view.findViewById(R.id.chatmyContent);
//        chatmyDate = view.findViewById(R.id.chatmyDate);
        if (mainHolder.chatopponame != null){
            mainHolder.chatopponame.setText(nickname[roomId]);
            mainHolder.chatoppoContent.setText(content[roomId][0]);
            mainHolder.chatoppoDate.setText(date[roomId][0]);
            mainHolder.chatoppoProfileImg.setImageResource(profile[roomId]);
            if(roomId == 2 || roomId == 3){
                mainHolder.chatmyProfileImg.setImageResource(R.drawable.profilecalm);
                mainHolder.chatmyContent.setText(this.content[roomId][1]);
                mainHolder.chatmyDate.setText(date[roomId][1]);
                mainHolder.rightchat.setVisibility(View.VISIBLE);
            }

        }
    }
    @Override
    public int getItemCount() {
        return 1;
    }

}

