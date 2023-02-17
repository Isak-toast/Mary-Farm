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

public class RecommendFragment3 extends Fragment {
    private ImageButton mButtonWaterHigh;
    private ImageButton mButtonWaterNormal;
    private ImageButton mButtonWaterLow;
    private ImageButton mButtonWaterEmpty;

    private RecommendActivity recommendActivity;

    public RecommendFragment3() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recommend3, container, false);

        recommendActivity = (RecommendActivity) getActivity();

        mButtonWaterHigh = view.findViewById(R.id.section_1);
        mButtonWaterNormal = view.findViewById(R.id.section_2);
        mButtonWaterLow = view.findViewById(R.id.section_3);
        mButtonWaterEmpty = view.findViewById(R.id.section_4);

        mButtonWaterHigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recommendActivity.setButtonValue3(053001);
                Log.i(TAG, "onClick: 물 많이를 선택함, 053001");

                // 프래그먼트 교체
                RecommendActivity recommendActivity = (RecommendActivity) getActivity();
                ViewPager2 recommendViewPager = recommendActivity.findViewById(R.id.recommendViewPager);
                recommendViewPager.setCurrentItem(3);

            }
        });

        mButtonWaterNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recommendActivity.setButtonValue3(053002);
                Log.i(TAG, "onClick: 물 꾸준히를 선택함, 053002");

                // 프래그먼트 교체
                RecommendActivity recommendActivity = (RecommendActivity) getActivity();
                ViewPager2 recommendViewPager = recommendActivity.findViewById(R.id.recommendViewPager);
                recommendViewPager.setCurrentItem(3);
            }
        });

        mButtonWaterLow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recommendActivity.setButtonValue3(053003);
                Log.i(TAG, "onClick: 물 적음을 선택함, 053003");

                // 프래그먼트 교체
                RecommendActivity recommendActivity = (RecommendActivity) getActivity();
                ViewPager2 recommendViewPager = recommendActivity.findViewById(R.id.recommendViewPager);
                recommendViewPager.setCurrentItem(3);
            }
        });

        mButtonWaterEmpty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recommendActivity.setButtonValue3(053004);
                Log.i(TAG, "onClick: 물 매우 적음을 선택함, 053004");

                // 프래그먼트 교체
                RecommendActivity recommendActivity = (RecommendActivity) getActivity();
                ViewPager2 recommendViewPager = recommendActivity.findViewById(R.id.recommendViewPager);
                recommendViewPager.setCurrentItem(3);
            }
        });
        return view;
    }
}