package com.numberONE.maryfarm.ui.AlgorithmPage;

import static android.content.ContentValues.TAG;

import android.content.Intent;
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
import com.numberONE.maryfarm.ui.AlgorithmPage.sample.RecommendSample1;
import com.numberONE.maryfarm.ui.AlgorithmPage.sample.RecommendSample2;
import com.numberONE.maryfarm.ui.AlgorithmPage.sample.RecommendSample3;

public class RecommendFragment5 extends Fragment {
    private ImageButton mButtonlightHigh;
    private ImageButton mButtonlightNormal;
    private ImageButton mButtonlightLow;

    private RecommendActivity recommendActivity;

    public RecommendFragment5() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend5, container, false);

        recommendActivity = (RecommendActivity) getActivity();

        mButtonlightHigh = view.findViewById(R.id.section_1);
        mButtonlightNormal = view.findViewById(R.id.section_2);
        mButtonlightLow = view.findViewById(R.id.section_3);

        mButtonlightHigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recommendActivity.setButtonValue5(55001);
                Log.i(TAG, "onClick: 매우 밝음을 선택함, 055001");

                recommendActivity.makeApiCall();
                Log.i(TAG, "요청 가즈아~ JSON은 뭐야!!");


//                Intent intent_1=new Intent(getActivity(),RecommendSample1.class);
//                startActivity(intent_1);

                Intent intent_2=new Intent(getActivity(), RecommendSample2.class);
                startActivity(intent_2);

//                Intent intent_3=new Intent(getActivity(), RecommendSample3.class);
//                startActivity(intent_3);

//                RecommendActivity recommendActivity = (RecommendActivity) getActivity();
//                ViewPager2 recommendViewPager = recommendActivity.findViewById(R.layout.fragment_recommend_example1);
            }
        });

        mButtonlightNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                recommendActivity.setButtonValue5(55002);
//                Log.i(TAG, "onClick: 밝음을 선택함, 055002");
//
//                recommendActivity.makeApiCall();
//                Log.i(TAG, "요청 가즈아~ JSON은 뭐야!!");

                recommendActivity.setButtonValue5(55002);
                Log.i(TAG, "onClick: 밝음을 선택함, 055002");

//                Intent intent_1=new Intent(getActivity(),RecommendSample1.class);
//                startActivity(intent_1);

//                Intent intent_2=new Intent(getActivity(), RecommendSample2.class);
//                startActivity(intent_2);

                Intent intent_3=new Intent(getActivity(), RecommendSample3.class);
                startActivity(intent_3);
            }
        });

        mButtonlightLow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recommendActivity.setButtonValue5(55003);
                Log.i(TAG, "onClick: 어두움을 선택함, 055003");

                recommendActivity.makeApiCall();
                Log.i(TAG, "요청 가즈아~ JSON은 뭐야!!");


                                Intent intent_1=new Intent(getActivity(),RecommendSample1.class);
                startActivity(intent_1);

//                Intent intent_2=new Intent(getActivity(), RecommendSample2.class);
//                startActivity(intent_2);

//                Intent intent_3=new Intent(getActivity(), RecommendSample3.class);
//                startActivity(intent_3);
            }
        });
        return view;
    }
}