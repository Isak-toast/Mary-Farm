package com.numberONE.maryfarm.ui.board;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.numberONE.maryfarm.R;
import com.numberONE.maryfarm.Retrofit.Board.BoardArticle;
import com.numberONE.maryfarm.Retrofit.RetrofitApiSerivce;
import com.numberONE.maryfarm.Retrofit.RetrofitClient;
import com.numberONE.maryfarm.databinding.FragmentBoardMainBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardMainFragment extends Fragment {
    private static final String TAG = "BoardMainFragment";

    FragmentBoardMainBinding binding;

    RecyclerView recyclerView_board;

    RecyclerView.LayoutManager layoutManager_board;

    RecyclerView.Adapter adapter_board;

    List<BoardArticle> article;
    
    public BoardMainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding =FragmentBoardMainBinding.inflate(inflater,container,false);

//       ------- 지역 선택 스피너 서버 통신 ----------
        // 지역 선택하면 서버에 요청보내서 지역에 맞는 게시판 정보 불러오기

        String region = binding.boardSpinner.getSelectedItem().toString();

        RetrofitApiSerivce service =RetrofitClient.getInstance().create(RetrofitApiSerivce.class);
        Log.d(TAG, " 지역 게시판에 넣어준 지역 :  " +region);
        service.postRegion(region).enqueue(new Callback<List<BoardArticle>>() {
            @Override
            public void onResponse(Call<List<BoardArticle>> call, Response<List<BoardArticle>> response) {
                Log.d(TAG, " 지역에 맞는 게시글들 불러오기 " );
                Log.d(TAG, "postRegion res.code : " + response.code());
                Log.d(TAG, "postRegion res body : " + response.body());
                if(response.isSuccessful()){
                    article=response.body();
                    Log.d(TAG, "postRegion 서버 통신 완료");
                    Log.d(TAG, "postRegion 서버 코드 " +response.code());
                }
            }

            @Override
            public void onFailure(Call<List<BoardArticle>> call, Throwable t) {
                Log.d(TAG, "postRegion 서버 통신 오류 ");
            }
        });
//       ------- 지역 선택 스피너 서버 통신 종료 ----------


//        ---- 게시판 글 작성 버튼 -----
        binding.boardEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BoardWriteFragment fragment =new BoardWriteFragment();
                FragmentManager fragmentManager= getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_activity,fragment).commitAllowingStateLoss();
            }
        });
//        ---- 게시판 글 작성 버튼 종료 -----



        ViewGroup view =binding.getRoot();
        return view;
    }


//         -------- 리사이클러 뷰  -----------
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView_board=binding.boardRecycler;

        recyclerView_board.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        layoutManager_board=new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        recyclerView_board.setLayoutManager(layoutManager_board);


        adapter_board=new BoardAdapter(article);

        recyclerView_board.setAdapter(adapter_board);
    }
//         -------- 리사이클러 뷰 종료 -----------

}