package com.numberONE.maryfarm.ui.home.Calendar;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.numberONE.maryfarm.R;
import com.numberONE.maryfarm.Retrofit.Calendar.MemoModel;

import java.util.List;

// 일자 선택 시 해당 일자에 키우고 있는 작물 정보 리스트 반환하는 리싸이클러뷰 어댑터~
public class CalendarPickPlantAdapter extends RecyclerView.Adapter<CalendarPickPlantAdapter.ViewHolder> {
    private List<MemoModel> memo;
    ViewHolder viewHolder;
    static int water = 0;
    static int scissors = 0;
    static int pill = 0;
    static int shovel = 0;
    static int note = 0;

    // 리사이클러 뷰로 들어갈 내용들 받는 부분
    public CalendarPickPlantAdapter(List<MemoModel> memo){
        this.memo = memo;
        Log.i(TAG, "CalendarPickPlantAdapter: "+memo.get(0).division);
    }
    //인터페이스 선언
    public interface OnItemClickListener{
        //클릭시 동작할 함수
        void onItemClick(View v, int pos, int id);
    }

    // 클릭 이벤트
    private static OnItemClickListener onItemClickListener = null;

    public static void setOnItemClickListener(OnItemClickListener listener){
        onItemClickListener = listener;
    }
// 뷰홀더
    public static class ViewHolder extends  RecyclerView.ViewHolder{
        public TextView plantsNameTextView;
        public ImageButton calendar_water;
        public ImageButton calendar_scissors;
        public ImageButton calendar_pill;
        public ImageButton calendar_shovel;
        public ImageButton calendar_note;
        public EditText calendar_plant_memo;
        public Button calendar_memo_save_btn;
        Button.OnClickListener clickListener;

        public ViewHolder(View view){
            super(view);
            plantsNameTextView = view.findViewById(R.id.textView);
            calendar_water = view.findViewById(R.id.calendar_water);
            calendar_scissors = view.findViewById(R.id.calendar_scissors);
            calendar_pill = view.findViewById(R.id.calendar_pill);
            calendar_shovel = view.findViewById(R.id.calendar_shovel);
            calendar_note = view.findViewById(R.id.calendar_note);
            calendar_plant_memo = view.findViewById(R.id.calendar_plant_memo);
            calendar_memo_save_btn = view.findViewById(R.id.calendar_memo_save_btn);

//            calendar_pill.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //존재하는 포지션인지 확인
//                    int pos = getAdapterPosition();
//                    if(pos != RecyclerView.NO_POSITION){
//                        //동작 호출 (onItemClick 함수 호출)
//                        if(onItemClickListener != null){
//                            onItemClickListener.onItemClick(v, pos);
//                        }
//                    }
//                }
//            });
            clickListener = v -> {
                int pos = getAdapterPosition();
                if(pos != RecyclerView.NO_POSITION){
                    if(onItemClickListener != null) {
//                        onItemClickListener.onItemClick(v, pos, v.getId());
                        switch (v.getId()) {
                            case R.id.calendar_water:
                                water = 1 - water;
                                if ( water == 1 ) {
                                    calendar_water.setImageResource(R.drawable.calendar_water_color);
                                } else {
                                    calendar_water.setImageResource(R.drawable.calendar_water);
                                }
                                onItemClickListener.onItemClick(v, pos, water);
                                break;
                            case R.id.calendar_scissors:
                                scissors = 1 - scissors;
                                if ( scissors == 1 ) {
                                    calendar_scissors.setImageResource(R.drawable.calendar_scissors_color);
                                } else {
                                    calendar_scissors.setImageResource(R.drawable.calendar_scissors);
                                }
                                onItemClickListener.onItemClick(v, pos, scissors);
                                break;
                            case R.id.calendar_pill:
                                pill = 1 - pill;
                                if ( pill == 1 ) {
                                    calendar_pill.setImageResource(R.drawable.calendar_pill_color);
                                } else {
                                    calendar_pill.setImageResource(R.drawable.calendar_pill);
                                }
                                onItemClickListener.onItemClick(v, pos, pill);
                                break;
                            case R.id.calendar_shovel:
                                shovel = 1 - shovel;
                                if ( shovel == 1 ) {
                                    calendar_shovel.setImageResource(R.drawable.calendar_shovel_color);
                                } else {
                                    calendar_shovel.setImageResource(R.drawable.calendar_shovel);
                                }
                                onItemClickListener.onItemClick(v, pos, shovel);
                                break;
                            case R.id.calendar_note:
                                note = 1 - note;
                                if ( note == 1 ) {
                                    calendar_note.setImageResource(R.drawable.calendar_note_color);
                                    calendar_plant_memo.setVisibility(View.VISIBLE);
                                    calendar_memo_save_btn.setVisibility(View.VISIBLE);
                                } else {
                                    calendar_note.setImageResource(R.drawable.calendar_note);
                                    calendar_plant_memo.setVisibility(View.GONE);
                                    calendar_memo_save_btn.setVisibility(View.GONE);
                                }
                                onItemClickListener.onItemClick(v, pos, note);
                                break;
//                            case R.id.calendar_memo_save_btn:

                        }
                    }
                }
            };
            calendar_water.setOnClickListener(clickListener);
            calendar_scissors.setOnClickListener(clickListener);
            calendar_pill.setOnClickListener(clickListener);
            calendar_shovel.setOnClickListener(clickListener);
            calendar_note.setOnClickListener(clickListener);
        };
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View holderView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_calendar_pick_plant, parent, false);
        viewHolder = new ViewHolder(holderView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder mainHolder, int i) {
        mainHolder.plantsNameTextView.setText(this.memo.get(i).getPlant().getPlantName() +"\n"+this.memo.get(i).getPlant().getCreatedAt().toString().substring(0, 10));
        Log.i(TAG, "onBindViewHolder:" + this.memo.get(i).getPlant().getPlantName());
        //        this.water = get(i).water;
//        this.water = get(i).water;
//        this.water = get(i).water;
//        this.water = get(i).water;
//        this.water = get(i).water;
    }

    @Override
    public int getItemCount() {
        return memo.size();
    }

}

