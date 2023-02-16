package com.numberONE.maryfarm.ui.alarm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.numberONE.maryfarm.R;
import com.numberONE.maryfarm.ui.home.Calendar.CalendarPlantsAdapter;

import de.hdodenhof.circleimageview.CircleImageView;

public class AlarmItemsAdapter extends RecyclerView.Adapter<AlarmItemsAdapter.ViewHolder> {
    private String[] nickname, content, date;
    private int[] profile;
    ViewHolder viewHolder;

    public AlarmItemsAdapter(String[] nickname, String[] content, String[] date, int[] profile){
        this.nickname = nickname;
        this.content = content;
        this.profile = profile;
        this.date = date;
    }
    // 커스텀 리스너 인터페이스 정의
    public interface OnChatClickListener {
        // 클릭 시 동작할 함수
        void onChatClick(View v, int position) ;
    }

    // 리스너 객체 참조를 저장하는 변수
    private static OnChatClickListener onChatClickListener = null ;

    // OnItemClickListener 리스너 객체 참조를 어댑터에 전달하는 메서드
    public static void setOnPlantCheckListener(OnChatClickListener listener) {
        onChatClickListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView nickname, content, date;
        public CircleImageView profile;

        public ViewHolder(View view){
            super(view);
            this.nickname = view.findViewById(R.id.alarmNickname);
            this.content = view.findViewById(R.id.alarmContent);
            this.profile = view.findViewById(R.id.alarmProfile);
            this.date = view.findViewById(R.id.alarmDate);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition() ;
                    if (pos != RecyclerView.NO_POSITION) {
                        if (onChatClickListener != null) {
                            onChatClickListener.onChatClick(v, pos);
                        }}}
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View holderView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_alarms, parent, false);
        viewHolder = new ViewHolder(holderView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder mainHolder, int i) {
        mainHolder.nickname.setText(this.nickname[i]);
        mainHolder.content.setText(this.content[i]);
        mainHolder.date.setText(this.date[i]);
        mainHolder.profile.setImageResource(this.profile[i]);
    }

    @Override
    public int getItemCount() { return nickname.length;}

}
