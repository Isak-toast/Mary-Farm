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

        recyclerView_alarms = binding.alarmNoti;
        layoutManager_alarms = new LinearLayoutManager(getActivity());
        recyclerView_alarms.setLayoutManager(layoutManager_alarms);

        String[] nickname = {"김관섭", "박수용", "이석우", "조민규"};
        String[] content = {"님이 새로운 게시물을 올렸습니다.","님이 새로운 게시물을 올렸습니다.","님이 새로운 게시물을 올렸습니다.","님이 새로운 게시물을 올렸습니다."};
        String[] date = {"1시간전","2일전","3일전","4일전"};
        int[] profile = {R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d};

        adapter_alarms = new AlarmItemsAdapter(nickname, date, content, profile);

        recyclerView_alarms.setAdapter(adapter_alarms);

        ViewGroup view = binding.getRoot();
        return view;
    }

}