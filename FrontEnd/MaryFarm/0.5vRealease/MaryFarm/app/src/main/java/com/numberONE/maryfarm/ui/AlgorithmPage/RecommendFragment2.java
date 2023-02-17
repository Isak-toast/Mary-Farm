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
    private ImageButton mButtonFruits;
    private ImageButton mButtonFlowers;

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

        mButtonFruits = view.findViewById(R.id.section_1);
        mButtonFlowers = view.findViewById(R.id.section_2);

        String baseparser = new String("ignSeasonChk");

        mButtonFruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recommendActivity.setButtonValue2(055001);
                Log.i(TAG, "onClick: 열매을 선택함, 073001");

                // 프래그먼트 교체
                RecommendActivity recommendActivity = (RecommendActivity) getActivity();
                ViewPager2 recommendViewPager = recommendActivity.findViewById(R.id.recommendViewPager);
                recommendViewPager.setCurrentItem(2);
            }
        });

        mButtonFlowers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recommendActivity.setButtonValue2(055002);
                Log.i(TAG, "onClick: 꽃을 선택함, 073002");

                // 프래그먼트 교체
                RecommendActivity recommendActivity = (RecommendActivity) getActivity();
                ViewPager2 recommendViewPager = recommendActivity.findViewById(R.id.recommendViewPager);
                recommendViewPager.setCurrentItem(2);
            }
        });


        return view;
    }
}