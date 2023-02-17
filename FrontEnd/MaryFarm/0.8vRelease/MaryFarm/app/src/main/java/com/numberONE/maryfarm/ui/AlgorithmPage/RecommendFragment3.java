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
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.numberONE.maryfarm.R;

public class RecommendFragment3 extends Fragment {
    private ImageButton mButton_1;
    private ImageButton mButton_2;
    private ImageButton mButton_3;
    private ImageButton mButton_4;
    private ImageButton mButton_5;
    private ImageButton mButton_6;
    private ImageButton mButton_7;

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

        mButton_1 = view.findViewById(R.id.section_1);
        mButton_2 = view.findViewById(R.id.section_2);
        mButton_3 = view.findViewById(R.id.section_3);
        mButton_4 = view.findViewById(R.id.section_4);
        mButton_5 = view.findViewById(R.id.section_5);
        mButton_6 = view.findViewById(R.id.section_6);
        mButton_7 = view.findViewById(R.id.section_7);

        mButton_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recommendActivity.setButtonValue3(69001);
                Log.i(TAG, "onClick: 녹색을 선택함, 069001");

                // 프래그먼트 교체
                RecommendActivity recommendActivity = (RecommendActivity) getActivity();
                ViewPager2 recommendViewPager = recommendActivity.findViewById(R.id.recommendViewPager);
                recommendViewPager.setCurrentItem(3);
            }
        });
        mButton_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recommendActivity.setButtonValue3(69002);
                Log.i(TAG, "onClick: 노란색을 선택함, 069002");

                // 프래그먼트 교체
                RecommendActivity recommendActivity = (RecommendActivity) getActivity();
                ViewPager2 recommendViewPager = recommendActivity.findViewById(R.id.recommendViewPager);
                recommendViewPager.setCurrentItem(3);
            }
        });
        mButton_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recommendActivity.setButtonValue3(69003);
                Log.i(TAG, "onClick: 흰색을 선택함, 069003");

                // 프래그먼트 교체
                RecommendActivity recommendActivity = (RecommendActivity) getActivity();
                ViewPager2 recommendViewPager = recommendActivity.findViewById(R.id.recommendViewPager);
                recommendViewPager.setCurrentItem(3);
            }
        });
        mButton_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recommendActivity.setButtonValue3(69004);
                Log.i(TAG, "onClick: 회색을 선택함, 069004");

                // 프래그먼트 교체
                RecommendActivity recommendActivity = (RecommendActivity) getActivity();
                ViewPager2 recommendViewPager = recommendActivity.findViewById(R.id.recommendViewPager);
                recommendViewPager.setCurrentItem(3);
            }
        });
        mButton_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recommendActivity.setButtonValue3(69005);
                Log.i(TAG, "onClick: 빨강을 선택함, 069005");

                // 프래그먼트 교체
                RecommendActivity recommendActivity = (RecommendActivity) getActivity();
                ViewPager2 recommendViewPager = recommendActivity.findViewById(R.id.recommendViewPager);
                recommendViewPager.setCurrentItem(3);
            }
        });
        mButton_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recommendActivity.setButtonValue3(69007);
                Log.i(TAG, "onClick: 기타 색을 선택함, 069007");

                // 프래그먼트 교체
                RecommendActivity recommendActivity = (RecommendActivity) getActivity();
                ViewPager2 recommendViewPager = recommendActivity.findViewById(R.id.recommendViewPager);
                recommendViewPager.setCurrentItem(3);
            }
        });
        mButton_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recommendActivity.setButtonValue3(69007);
                Log.i(TAG, "onClick: 혼합 색상을 선택함, 069007");

                // 프래그먼트 교체
                RecommendActivity recommendActivity = (RecommendActivity) getActivity();
                ViewPager2 recommendViewPager = recommendActivity.findViewById(R.id.recommendViewPager);
                recommendViewPager.setCurrentItem(3);
            }
        });

        return view;
    }
}