package com.numberONE.maryfarm.ui.AlgorithmPage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.numberONE.maryfarm.R;

public class RecommendFragment extends Fragment {

    private String title;

    public RecommendFragment(String title) {

        this.title = title;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_recommend, container, false);
        TextView recommendTextView = root.findViewById(R.id.recommendTextView);
        recommendTextView.setText(title);
        return root;
    }
}