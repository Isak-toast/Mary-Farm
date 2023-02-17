package com.numberONE.maryfarm.ui.home.Calendar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.numberONE.maryfarm.R;

// 달력 진입 시 키우고 있는 작물 리스트 체크박스 반환~
public class CalendarPlantsAdapter extends RecyclerView.Adapter<CalendarPlantsAdapter.ViewHolder> {
    public static String[] plantName, createdAt, harvestTime;
    ViewHolder viewHolder;



    // 리사이클러 뷰로 들어갈 내용들 받는 부분
    public CalendarPlantsAdapter(String[] plantName, String[] createdAt, String[] harvestTime){
        CalendarPlantsAdapter.plantName = plantName;
        CalendarPlantsAdapter.createdAt = createdAt;
        CalendarPlantsAdapter.harvestTime = harvestTime;
    }
    // 커스텀 리스너 인터페이스 정의
    public interface OnPlantCheckListener {
    // 클릭 시 동작할 함수
        void onPlantCheck(View v, int position, Boolean isChecked, String createdAt, String harvestTime, Integer checkboxId) ;

    }

    // 리스너 객체 참조를 저장하는 변수
    private static OnPlantCheckListener onPlantCheckListener = null ;

    // OnItemClickListener 리스너 객체 참조를 어댑터에 전달하는 메서드
    public static void setOnPlantCheckListener(OnPlantCheckListener listener) {
        onPlantCheckListener = listener;
    }
// 뷰홀더
    public static class ViewHolder extends  RecyclerView.ViewHolder{
        public TextView checkboxtitle;
        public CheckBox checkbox;

        public ViewHolder(View view){
            super(view);
            checkboxtitle = view.findViewById(R.id.check_box_text);
            checkbox = view.findViewById(R.id.check_plant_appearance);

            //리스너 객체 전달 메서드와 변수 추가
            //아이템 클릭 시, 커스텀 이벤트 메서드를 호출
            checkbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 존재하는 포지션인지 확인
                    int pos = getAdapterPosition() ;
                    if (pos != RecyclerView.NO_POSITION) {
                        // 리스너 객체의 메서드 호출.
                        if (onPlantCheckListener != null) {

                            // 날짜 표시하러 ㄱㄱ
                            Boolean checked = checkbox.isChecked();
                            onPlantCheckListener.onPlantCheck(v, pos, checked, createdAt[pos], harvestTime[pos], checkbox.getId());
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View holderView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_calendar_plant, parent, false);
        viewHolder = new ViewHolder(holderView);

//        holderView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int position = viewHolder.getAdapterPosition();
//                if (position != RecyclerView.NO_POSITION) {
//
//                }
//                mListener.onPlantCheckClick(v, position);
//            }
//        });

        return viewHolder;
    }
// ViewHolder viewType
    @Override
    public void onBindViewHolder(@NonNull ViewHolder mainHolder, int i) {
        mainHolder.checkboxtitle.setText(this.plantName[i]+"\n"+this.createdAt[i]);
    }

    @Override
    public int getItemCount() {
        return plantName.length;
    }

}

