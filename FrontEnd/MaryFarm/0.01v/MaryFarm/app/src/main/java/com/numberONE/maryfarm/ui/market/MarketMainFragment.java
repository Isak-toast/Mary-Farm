package com.numberONE.maryfarm.ui.market;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.numberONE.maryfarm.MainActivity;
import com.numberONE.maryfarm.R;
import com.numberONE.maryfarm.ui.board.BoardMainFragment;
import com.numberONE.maryfarm.ui.home.HomeFragment;

public class MarketMainFragment extends Fragment implements MainActivity.OnBackPressedListener{

    private static final String TAG="MarketMainFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_market_main, container, false);
    }


    //    ---- 뒤로가기 버튼 로직 (몸통은 메인액티비티에 ) -------
    @Override
    public void onBack() {
        HomeFragment fragment =new HomeFragment();
        Log.d(TAG, " 뒤로가기 버튼 실행  ");
        //리스너를 설정하기 위해 Activity 받아오기
        MainActivity activity = (MainActivity)getActivity();
        // 한번 뒤로가기 버튼을 눌렀다면 Listener를 null 로 해제
        activity.setOnBackPressedListener(null);
        // 보드 메인으로 이동
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_activity,fragment).commit();
    }

    //   Fragment 호출 시 반드시 호출되는 오바라이드 메소드
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach: ");
        ((MainActivity)context).setOnBackPressedListener(this);
    }
}