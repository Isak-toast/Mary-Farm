package com.numberONE.maryfarm.ui.board;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

//      ----- 등록 버튼 로직 ( 레트로핏 요청 보내고 , 해당 게시판 페이지로 이동 )  -----
        binding.writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//              --- 요청에 담을 데이터들 가져오기 ---
                String title = binding.title.getText().toString(); // 제목
                String region =binding.boardTypeSpinner.getSelectedItem().toString(); // 지역 (스피너)
                String content = binding.content.getText().toString(); // 내용


//              ----- shared  고유아이디 번호, 이름 , 프로필 사진  ------
                SharedPreferences preferences = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
                String id= preferences.getString("pref_id","pref_id_null");
                String name =preferences.getString("pref_name","pref_name_null");
                String image=preferences.getString("pref_img","pref_img_null");
                Log.d(TAG, "Shared Pre _ test -  id:" + id +" name : "+name +" image : "+image );
//              ----- shared  종료 --------

                BoardWrite boardWrite = new BoardWrite();
                boardWrite.setUserId(id);
                boardWrite.setUserName(name);
                boardWrite.setProfilePath(image);
                boardWrite.setType(region);
                boardWrite.setTitle(title);
                boardWrite.setContent(content);

                Log.d(TAG, "boardWrite 담긴 정보 :  "+ boardWrite.toString().trim());
//                ---- 데이터 담기 끝 ----

//                ----- 서버 요청 로직 -----
                RetrofitApiSerivce service= RetrofitClient.getInstance().create(RetrofitApiSerivce.class);
                service.writeArticle(boardWrite).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Log.d(TAG, "onResponse: + call : "+ call.toString().trim() );
                        Log.d(TAG, "글작성 api : onResponse res.body"+response.body() );
                        Log.d(TAG, "글작성 res.code "+response.code());

                        if(response.isSuccessful() && !region.equals("지역 선택")){
                            Log.d(TAG, " 게시판 글 작성 서버 통신 성공 ");
                            // 게시판 메인으로 가서 글 작성한 지역 게시판으로 가기위해 정보 넘겨주기
                            SharedPreferences preferences= getActivity().getSharedPreferences("board",Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("type",region);
                            editor.commit();
                            // 게시판 메인으로 이동하기
                            FragmentManager manager =getActivity().getSupportFragmentManager();
                            manager.beginTransaction().replace(R.id.main_activity,fragment).commitAllowingStateLoss();

                        }
                        if(response.code() == 500 || region.equals("지역 선택")){
                            Toast.makeText(getActivity(),"지역을 선택해야 합니다.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
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