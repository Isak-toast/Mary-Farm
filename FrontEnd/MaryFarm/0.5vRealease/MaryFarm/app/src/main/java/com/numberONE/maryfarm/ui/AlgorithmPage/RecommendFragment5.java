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

public class RecommendFragment5 extends Fragment {
    private ImageButton mButtonlightHigh;
    private ImageButton mButtonlightNormal;
    private ImageButton mButtonlightLow;

    private RecommendActivity recommendActivity;

    public RecommendFragment5() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recommend5, container, false);

        recommendActivity = (RecommendActivity) getActivity();

        mButtonlightHigh = view.findViewById(R.id.section_1);
        mButtonlightNormal = view.findViewById(R.id.section_2);
        mButtonlightLow = view.findViewById(R.id.section_3);

        mButtonlightHigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recommendActivity.setButtonValue5(055001);
                Log.i(TAG, "onClick: 매우 밝음을 선택함, 055001");

                recommendActivity.makeApiCall();
                Log.i(TAG, "요청 가즈아~ JSON은 뭐야!!");


                // 프래그먼트 교체
                // 여기는 마지막 페이지라서 다른 곳으로 가야함
//                RecommendActivity recommendActivity = (RecommendActivity) getActivity();
//                ViewPager2 recommendViewPager = recommendActivity.findViewById(R.id.recommendViewPager);
//                recommendViewPager.setCurrentItem(3)

            }
        });

        mButtonlightNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recommendActivity.setButtonValue5(05502);
                Log.i(TAG, "onClick: 밝음을 선택함, 055002");

                recommendActivity.makeApiCall();
                Log.i(TAG, "요청 가즈아~ JSON은 뭐야!!");
                // 프래그먼트 교체
                // 여기는 마지막 페이지라서 다른 곳으로 가야함
//                RecommendActivity recommendActivity = (RecommendActivity) getActivity();
//                ViewPager2 recommendViewPager = recommendActivity.findViewById(R.id.recommendViewPager);
//                recommendViewPager.setCurrentItem(3)
            }
        });

        mButtonlightLow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recommendActivity.setButtonValue5(055003);
                Log.i(TAG, "onClick: 어두움을 선택함, 055003");

                recommendActivity.makeApiCall();
                Log.i(TAG, "요청 가즈아~ JSON은 뭐야!!");
                // 프래그먼트 교체
                // 여기는 마지막 페이지라서 다른 곳으로 가야함
//                RecommendActivity recommendActivity = (RecommendActivity) getActivity();
//                ViewPager2 recommendViewPager = recommendActivity.findViewById(R.id.recommendViewPager);
//                recommendViewPager.setCurrentItem(3);
            }
        });
        return view;
    }
}