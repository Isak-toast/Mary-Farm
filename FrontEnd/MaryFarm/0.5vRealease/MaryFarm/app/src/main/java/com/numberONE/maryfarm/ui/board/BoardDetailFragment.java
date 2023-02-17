package com.numberONE.maryfarm.ui.board;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.numberONE.maryfarm.Diary.CommentAdapter;
import com.numberONE.maryfarm.R;
import com.numberONE.maryfarm.Retrofit.Board.BoardArticle;
import com.numberONE.maryfarm.Retrofit.RetrofitApiSerivce;
import com.numberONE.maryfarm.Retrofit.RetrofitClient;
import com.numberONE.maryfarm.databinding.FragmentBoardDetailBinding;
import com.numberONE.maryfarm.ui.home.FarmFeed.FeedFollowersAdapter;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardDetailFragment extends Fragment {

    private static final String TAG = "BoardDetailFragment";

    FragmentBoardDetailBinding binding;

    RecyclerView recyclerView_comment;
    RecyclerView.LayoutManager layoutManager_comment;
    RecyclerView.Adapter adapter_comment;

    BoardArticle boardArticle=new BoardArticle();

    public BoardDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding=FragmentBoardDetailBinding.inflate(inflater,container,false);

        hideBottomNavigation(true); // 바텀 네비 비활성화

        recyclerView_comment=binding.boardDetailCommentRecyclerView;
        layoutManager_comment=new LinearLayoutManager(getActivity());
        recyclerView_comment.setLayoutManager(layoutManager_comment);

        RetrofitApiSerivce service = RetrofitClient.getInstance().create(RetrofitApiSerivce.class);
//        articleId 꺼내와서 넣어주기
        service.getArticles("11").enqueue(new Callback<BoardArticle>() {
            @Override
            public void onResponse(Call<BoardArticle> call, Response<BoardArticle> response) {
                Log.d(TAG, "BoardDetail res.code :" + response.code());
                Log.d(TAG, "BoardDetail res.body : "+ response.body());
                if(response.isSuccessful()){
                    binding.boardDetailType.setText(response.body().getType());
                    binding.boardDetailTitle.setText(response.body().getTitle());
//                    binding.boardDetailProfile.setImageResource(response.body().get); // 프로필 이미지 처리
                    binding.boardDetailNickname.setText(response.body().getUserName());
//                    binding.boardDetailDate.setText(response.body().getLastModifiedDate()); // 날짜 처리 (형식 , 수정날짜기본값 무엇인지 체크 )
                    binding.boardDetailViewCnt.setText(response.body().getViews());
                    binding.boardDetailContent.setText(response.body().getContent());

                    HashMap<Object,Object> comments=response.body().getComments(); // 댓글들 list로 받아와야할지 체크
//                    comments.get()
                }

            }

            @Override
            public void onFailure(Call<BoardArticle> call, Throwable t) {
                t.printStackTrace();
                Log.d(TAG, "BoardDetail 서버 전송 실패 ");
            }
        });





        ViewGroup view =binding.getRoot();
        return view;
    }

    // 리사이클러 뷰
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView_comment=binding.boardDetailCommentRecyclerView;
        layoutManager_comment=new LinearLayoutManager(getActivity());
        recyclerView_comment.setLayoutManager(layoutManager_comment);

        adapter_comment=new BoardCommentAdapter();
        recyclerView_comment.setAdapter(adapter_comment);
    }

    // 이 프래그먼트에서 넘어갈 때 다시 바텀 네비 활성화
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        hideBottomNavigation(false);
    }

    //바텀 네비 숨기기 로직
    public void hideBottomNavigation(Boolean bool) {
        BottomNavigationView bottomNavigation = getActivity().findViewById(R.id.nav_view);
        if (bool == true)
            bottomNavigation.setVisibility(View.GONE);
        else
            bottomNavigation.setVisibility(View.VISIBLE);
    }

}