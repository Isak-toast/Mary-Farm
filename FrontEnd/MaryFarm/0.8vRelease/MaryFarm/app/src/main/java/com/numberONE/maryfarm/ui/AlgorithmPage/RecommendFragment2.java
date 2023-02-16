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

public class RecommendFragment2 extends Fragment {
    private ImageButton mButton_1;
    private ImageButton mButton_2;
    private ImageButton mButton_3;
    private ImageButton mButton_4;
    private ImageButton mButton_5;
    private ImageButton mButton_6;
    private ImageButton mButton_7;
    private ImageButton mButton_8;
    private ImageButton mButton_9;

    private RecommendActivity recommendActivity;

    public RecommendFragment2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recommend2, container, false);

        recommendActivity = (RecommendActivity) getActivity();

        mButton_1 = view.findViewById(R.id.section_1);
        mButton_2 = view.findViewById(R.id.section_2);
        mButton_3 = view.findViewById(R.id.section_3);
        mButton_4 = view.findViewById(R.id.section_4);
        mButton_5 = view.findViewById(R.id.section_5);
        mButton_6 = view.findViewById(R.id.section_6);
        mButton_7 = view.findViewById(R.id.section_7);
        mButton_8 = view.findViewById(R.id.section_8);
        mButton_9 = view.findViewById(R.id.section_9);

        mButton_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recommendActivity.setButtonValue2(071001);
                Log.i(TAG, "onClick: 파랑을 선택함, 071001");

                // 프래그먼트 교체
                RecommendActivity recommendActivity = (RecommendActivity) getActivity();
                ViewPager2 recommendViewPager = recommendActivity.findViewById(R.id.recommendViewPager);
                recommendViewPager.setCurrentItem(2);
            }
        });
        mButton_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recommendActivity.setButtonValue2(071002);
                Log.i(TAG, "onClick: 보라색을 선택함, 071002");

                // 프래그먼트 교체
                RecommendActivity recommendActivity = (RecommendActivity) getActivity();
                ViewPager2 recommendViewPager = recommendActivity.findViewById(R.id.recommendViewPager);
                recommendViewPager.setCurrentItem(2);
            }
        });
        mButton_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recommendActivity.setButtonValue2(071003);
                Log.i(TAG, "onClick: 분홍을 선택함, 071003");

                // 프래그먼트 교체
                RecommendActivity recommendActivity = (RecommendActivity) getActivity();
                ViewPager2 recommendViewPager = recommendActivity.findViewById(R.id.recommendViewPager);
                recommendViewPager.setCurrentItem(2);
            }
        });
        mButton_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recommendActivity.setButtonValue2(071004);
                Log.i(TAG, "onClick: 빨강을 선택함, 071004");

                // 프래그먼트 교체
                RecommendActivity recommendActivity = (RecommendActivity) getActivity();
                ViewPager2 recommendViewPager = recommendActivity.findViewById(R.id.recommendViewPager);
                recommendViewPager.setCurrentItem(2);
            }
        });
        mButton_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recommendActivity.setButtonValue2(071005);
                Log.i(TAG, "onClick: 오렌지색을 선택함, 071005");

                // 프래그먼트 교체
                RecommendActivity recommendActivity = (RecommendActivity) getActivity();
                ViewPager2 recommendViewPager = recommendActivity.findViewById(R.id.recommendViewPager);
                recommendViewPager.setCurrentItem(2);
            }
        });
        mButton_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recommendActivity.setButtonValue2(071007);
                Log.i(TAG, "onClick: 노랑색을 선택함, 071007");

                // 프래그먼트 교체
                RecommendActivity recommendActivity = (RecommendActivity) getActivity();
                ViewPager2 recommendViewPager = recommendActivity.findViewById(R.id.recommendViewPager);
                recommendViewPager.setCurrentItem(2);
            }
        });
        mButton_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recommendActivity.setButtonValue2(071007);
                Log.i(TAG, "onClick: 흰색을 선택함, 071007");

                // 프래그먼트 교체
                RecommendActivity recommendActivity = (RecommendActivity) getActivity();
                ViewPager2 recommendViewPager = recommendActivity.findViewById(R.id.recommendViewPager);
                recommendViewPager.setCurrentItem(2);
            }
        });
        mButton_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recommendActivity.setButtonValue2(071007);
                Log.i(TAG, "onClick: 혼합색을 선택함, 071007");

                // 프래그먼트 교체
                RecommendActivity recommendActivity = (RecommendActivity) getActivity();
                ViewPager2 recommendViewPager = recommendActivity.findViewById(R.id.recommendViewPager);
                recommendViewPager.setCurrentItem(2);
            }
        });
        mButton_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recommendActivity.setButtonValue2(071007);
                Log.i(TAG, "onClick: 기타 색상을 선택함, 071007");

                // 프래그먼트 교체
                RecommendActivity recommendActivity = (RecommendActivity) getActivity();
                ViewPager2 recommendViewPager = recommendActivity.findViewById(R.id.recommendViewPager);
                recommendViewPager.setCurrentItem(2);
            }
        });

        return view;
    }
}