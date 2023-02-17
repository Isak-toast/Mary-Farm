package com.numberONE.maryfarm.ui.board;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.numberONE.maryfarm.R;
import com.numberONE.maryfarm.Retrofit.Board.BoardArticle;
import com.numberONE.maryfarm.Retrofit.Board.BoardWrite;
import com.numberONE.maryfarm.Retrofit.RetrofitApiSerivce;
import com.numberONE.maryfarm.Retrofit.RetrofitClient;
import com.numberONE.maryfarm.databinding.FragmentBoardWriteBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardWriteFragment extends Fragment {

    private static final String TAG="BoardWriteFragment";

    FragmentBoardWriteBinding binding;

    BoardMainFragment fragment = new BoardMainFragment();

    public BoardWriteFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBoardWriteBinding.inflate(inflater,container,false);

//        ---- 취소 버튼 로직  -----
        binding.writeCloseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager =getActivity().getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.main_activity,fragment).commitAllowingStateLoss();
            }
        });

//        ----- 등록 버튼 로직 ( 레트로핏 요청 보내고 , 해당 게시판 페이지로 이동 )  -----
        binding.writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//              --- 요청에 담을 데이터들 가져오기 ---
                String title = binding.title.getText().toString(); // 제목
                String region =binding.boardTypeSpinner.getSelectedItem().toString(); // 지역 (스피너)
                String content = binding.content.getText().toString(); // 내용

                SharedPreferences preferences= getActivity().getSharedPreferences("boardwritefragment", Context.MODE_PRIVATE);
                String userid=preferences.getString("userId","shared_userId_null");
                String userName=preferences.getString("userName","shared_userName_null");
                String profilePath=preferences.getString("userProfile","shared_profile_null");

                BoardWrite boardWrite = new BoardWrite();
                boardWrite.setUserId(userid);
                boardWrite.setUserName(userName);
                boardWrite.setProfilePath(profilePath);
                boardWrite.setType(region);
                boardWrite.setTitle(title);
                boardWrite.setContent(content);

                Log.d(TAG, "boardWrite 담긴 정보 :  "+ boardWrite.toString().trim());
//                ---- 데이터 담기 끝 ----

//                ----- 서버 요청 로직 -----
                RetrofitApiSerivce service= RetrofitClient.getInstance().create(RetrofitApiSerivce.class);
                service.writeArticle(boardWrite).enqueue(new Callback<BoardWrite>() {
                    @Override
                    public void onResponse(Call<BoardWrite> call, Response<BoardWrite> response) {
                        Log.d(TAG, "onResponse: + call : "+ call.toString().trim() );
                        Log.d(TAG, "글작성 api : onResponse");
                        Log.d(TAG, "글작성 res.code "+response.code());
                        Log.d(TAG, "onResponse: "+response.body());
                        if(response.isSuccessful() && response.body()!=null){
                            Log.d(TAG, " 게시판 글 작성 서버 통신 성공 ");
                            FragmentManager manager =getActivity().getSupportFragmentManager();
                            manager.beginTransaction().replace(R.id.main_activity,fragment).commitAllowingStateLoss();
                        }
                    }

                    @Override
                    public void onFailure(Call<BoardWrite> call, Throwable t) {
                        t.printStackTrace();
                        Log.d(TAG, " 게시판 글 작성 서버 통신 실패 ");
                    }
                });

//               ------- 서버 요청 로직 끝 -----

            }
        });

        ViewGroup view =binding.getRoot();
        return view;
    }
}