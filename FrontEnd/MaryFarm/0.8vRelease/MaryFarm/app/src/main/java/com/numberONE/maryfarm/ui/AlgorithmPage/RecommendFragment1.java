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

public class RecommendFragment1 extends Fragment {
    private ImageButton mButtonSpring;
    private ImageButton mButtonSummer;
    private ImageButton mButtonAutumn;
    private ImageButton mButtonWinter;

    private RecommendActivity recommendActivity;

    public RecommendFragment1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recommend1, container, false);

        recommendActivity = (RecommendActivity) getActivity();

        mButtonSpring = view.findViewById(R.id.section_1);
        mButtonSummer = view.findViewById(R.id.section_2);
        mButtonAutumn = view.findViewById(R.id.section_3);
        mButtonWinter = view.findViewById(R.id.section_4);

        String baseparser = new String("ignSeasonChk");
        
        mButtonSpring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recommendActivity.setButtonValue1(73001);
                Log.i(TAG, "onClick: 봄을 선택함, 073001");

                // 프래그먼트 교체
                RecommendActivity recommendActivity = (RecommendActivity) getActivity();
                ViewPager2 recommendViewPager = recommendActivity.findViewById(R.id.recommendViewPager);
                recommendViewPager.setCurrentItem(1);

            }
        });

        mButtonSummer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                recommendActivity.setButtonValue1(73002);
                Log.i(TAG, "onClick: 여름을 선택함, 073002");

                // 프래그먼트 교체
                RecommendActivity recommendActivity = (RecommendActivity) getActivity();
                ViewPager2 recommendViewPager = recommendActivity.findViewById(R.id.recommendViewPager);
                recommendViewPager.setCurrentItem(1);
            }
        });

        mButtonAutumn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recommendActivity.setButtonValue1(73003);
                Log.i(TAG, "onClick: 가을을 선택함, 073003");

                // 프래그먼트 교체
                RecommendActivity recommendActivity = (RecommendActivity) getActivity();
                ViewPager2 recommendViewPager = recommendActivity.findViewById(R.id.recommendViewPager);
                recommendViewPager.setCurrentItem(1);
            }
        });

        mButtonWinter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recommendActivity.setButtonValue1(73004);
                Log.i(TAG, "onClick: 겨울을 선택함, 073004");

                // 프래그먼트 교체
                RecommendActivity recommendActivity = (RecommendActivity) getActivity();
                ViewPager2 recommendViewPager = recommendActivity.findViewById(R.id.recommendViewPager);
                recommendViewPager.setCurrentItem(1);
            }
        });
        return view;
    }
}