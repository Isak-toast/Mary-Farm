package com.numberONE.maryfarm.ui.alarm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.numberONE.maryfarm.MainActivity;
import com.numberONE.maryfarm.R;
import com.numberONE.maryfarm.databinding.FragmentAlarmBinding;

public class AlarmFragment extends Fragment {

    private FragmentAlarmBinding binding;
    RecyclerView recyclerView_alarms;
    RecyclerView.LayoutManager layoutManager_alarms;
    RecyclerView.Adapter adapter_alarms;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAlarmBinding.inflate(inflater,container,false);
        ViewGroup view = binding.getRoot();

        recyclerView_alarms = binding.alarmNoti;
        layoutManager_alarms = new LinearLayoutManager(getActivity());
        recyclerView_alarms.setLayoutManager(layoutManager_alarms);

        String[] nickname = {"왕감자", "김차분팬1호", "왕감자", "호박"};
        String[] content = {"님이 내 일지를 좋아해요.","님이 내 농장 이웃이 되었어요!","님이 새로운 일지를 시작했어요.","일지를 올린 지 3주가 지났어요!"};
        String[] date = {"1시간전","2일전","3일전","4일전"};
        int[] profile = {R.drawable.profilebaek,R.drawable.b,R.drawable.profilebaek,R.drawable.pumpkin};

        adapter_alarms = new AlarmItemsAdapter(nickname, date, content, profile);
        recyclerView_alarms.setAdapter(adapter_alarms);

        AlarmItemsAdapter.setOnPlantCheckListener(new AlarmItemsAdapter.OnChatClickListener() {
            @Override
            public void onChatClick(View v, int position) {
                ((MainActivity)getActivity()).onChatFragmentChange(2, ""+position+"");
            }
        });

        return view;
    }

}