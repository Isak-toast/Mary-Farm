package com.numberONE.maryfarm.ui.AlgorithmPage;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.numberONE.maryfarm.R;

public class RecommendFragment4 extends Fragment {
    private ImageButton mButtonWaterHigh;
    private ImageButton mButtonWaterNormal;
    private ImageButton mButtonWaterLow;

    private RecommendActivity recommendActivity;

    public RecommendFragment4() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recommend4, container, false);

        recommendActivity = (RecommendActivity) getActivity();

        mButtonWaterHigh = view.findViewById(R.id.section_1);
        mButtonWaterNormal = view.findViewById(R.id.section_2);
        mButtonWaterLow = view.findViewById(R.id.section_3);

        mButtonWaterHigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recommendActivity.setButtonValue4(00000);
                Log.i(TAG, "onClick: 소형 작물을 선택함, 00000");

                // 프래그먼트 교체
                RecommendActivity recommendActivity = (RecommendActivity) getActivity();
                ViewPager2 recommendViewPager = recommendActivity.findViewById(R.id.recommendViewPager);
                recommendViewPager.setCurrentItem(4);

            }
        });

        mButtonWaterNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recommendActivity.setButtonValue4(00000);
                Log.i(TAG, "onClick: 중형 작물을 선택함, 00000");

                // 프래그먼트 교체
                RecommendActivity recommendActivity = (RecommendActivity) getActivity();
                ViewPager2 recommendViewPager = recommendActivity.findViewById(R.id.recommendViewPager);
                recommendViewPager.setCurrentItem(4);
            }
        });

        mButtonWaterLow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recommendActivity.setButtonValue4(00000);
                Log.i(TAG, "onClick: 대형 작물을 선택함, 00000");

                // 프래그먼트 교체
                RecommendActivity recommendActivity = (RecommendActivity) getActivity();
                ViewPager2 recommendViewPager = recommendActivity.findViewById(R.id.recommendViewPager);
                recommendViewPager.setCurrentItem(4);
            }
        });
        return view;
    }
}