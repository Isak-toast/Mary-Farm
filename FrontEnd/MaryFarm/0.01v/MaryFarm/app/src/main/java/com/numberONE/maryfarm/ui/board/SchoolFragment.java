package com.numberONE.maryfarm.ui.board;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.youtube.player.YouTubePlayerView;
import com.numberONE.maryfarm.R;
import com.numberONE.maryfarm.databinding.FragmentSchoolBinding;
import com.numberONE.maryfarm.ui.chat.ChatAdapter;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;

import org.jetbrains.annotations.NotNull;

public class SchoolFragment extends Fragment {
    private LinearLayout recipeViewLinearLayout;
    private FragmentSchoolBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSchoolBinding.inflate(inflater, container, false);
        ViewGroup view = binding.getRoot();

//        recipeViewLinearLayout = binding.recipeViewLinearLayout;
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        params.bottomMargin = 30;

//        YouTubePlayerView ypv = new YouTubePlayerView(getActivity());
//        ypv.setLayoutParams(params);
//        recipeViewLinearLayout.addView(ypv);
//        getLifecycle().addObserver(ypv);
//        ypv.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
//            @Override
//            public void onReady(@NotNull YouTubePlayer youTubePlayer) {
//                youTubePlayer.loadVideo("비디오 아이디",0);
//            }
//        });

        return view;
    }
}