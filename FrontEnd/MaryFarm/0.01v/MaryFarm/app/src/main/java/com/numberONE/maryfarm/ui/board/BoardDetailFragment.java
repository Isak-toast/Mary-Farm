package com.numberONE.maryfarm.ui.board;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.numberONE.maryfarm.MainActivity;
import com.numberONE.maryfarm.R;
import com.numberONE.maryfarm.Retrofit.Board.BoardArticle;
import com.numberONE.maryfarm.Retrofit.Board.BoardComments;
import com.numberONE.maryfarm.Retrofit.Board.BoardWrite;
import com.numberONE.maryfarm.Retrofit.RetrofitApiSerivce;
import com.numberONE.maryfarm.Retrofit.RetrofitClient;
import com.numberONE.maryfarm.databinding.FragmentBoardDetailBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardDetailFragment extends Fragment implements MainActivity.OnBackPressedListener{

    private static final String TAG = "BoardDetailFragment";

    FragmentBoardDetailBinding binding;

    RecyclerView recyclerView_comment;
    RecyclerView.LayoutManager layoutManager_comment;
    RecyclerView.Adapter adapter_comment;

    BoardArticle boardArticle=new BoardArticle();
    List<BoardComments> comments = new ArrayList<>();

    private String URL = "https://s3.ap-northeast-2.amazonaws.com/maryfarm.bucket/";

    String region="";

    public BoardDetailFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding =FragmentBoardDetailBinding.inflate(inflater,container,false);

        hideBottomNavigation(true); // 바텀 네비 비활성화

//      -- 댓글 리사이클러뷰 --

        recyclerView_comment=binding.boardDetailCommentRecyclerView;
        layoutManager_comment=new LinearLayoutManager(getActivity());
        recyclerView_comment.setLayoutManager(layoutManager_comment);

//      -- 댓글 리사이클러뷰 끝 --


        RetrofitApiSerivce service = RetrofitClient.getInstance().create(RetrofitApiSerivce.class);
        // BoardMainFragment에서 넘겨준 articleId 가져오기
        SharedPreferences preferences= getActivity().getSharedPreferences("board", Context.MODE_PRIVATE);
        String articleId=preferences.getString("board_articleId","board_articleId_isNull");
        Log.d(TAG, "onCreateView : articleId  : " + articleId);
        service.getArticles(articleId).enqueue(new Callback<BoardArticle>() {
            @Override
            public void onResponse(Call<BoardArticle> call, Response<BoardArticle> response) {
                Log.d(TAG, "BoardDetail res.code :" + response.code());
                Log.d(TAG, "BoardDetail res.body : "+ response.body());
                if(response.isSuccessful()){
                    boardArticle = response.body();
                    Log.d(TAG, "boardArticle :"+ boardArticle.toString());

                    binding.boardDetailType.setText(boardArticle.getType());
                    binding.boardDetailTitle.setText(boardArticle.getTitle());
                    Glide.with(getActivity()).load(boardArticle.getProfilePath()).into(binding.boardDetailProfile);
                    binding.boardDetailNickname.setText(boardArticle.getUserName());
                    String date =DateToString(response.body().getLastModifiedDate());
                    binding.boardDetailDate.setText(date); // 날짜 처리 ( 작성일 ? 수정일 ? 무엇인지 체크 )
                    binding.boardDetailViewCnt.setText(boardArticle.getViews()+"");
                    binding.boardDetailContent.setText(boardArticle.getContent());
                    comments=boardArticle.getComments(); // 리스트로 댓글들 받아오기
//                    Log.d(TAG, "댓글 리스트 :  " + comments.get(0).toString());
//                    Log.d(TAG, "댓글 리스트 이름 :  " + comments.get(0).getUserName());
//                    Log.d(TAG, "댓글 리스트 프로필 사진 :  " + comments.get(0).getProfile());
                    // 댓글 리사이클러 뷰 어댑터 연결
                    adapter_comment=new BoardCommentAdapter(comments);
                    recyclerView_comment.setAdapter(adapter_comment);
                }
            }

            @Override
            public void onFailure(Call<BoardArticle> call, Throwable t) {
                t.printStackTrace();
                Log.d(TAG, "BoardDetail 서버 전송 실패 ");
            }
        });

//      ---------- 게시글 댓글 작성 로직 ----------------

        binding.boardDetailCommentAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "게시글 댓글 작성 버튼 클릭 ");
                SharedPreferences preferences_pref= getActivity().getSharedPreferences("pref",Context.MODE_PRIVATE);
                String userid=preferences_pref.getString("pref_id","pref_id_isNull");
                String username=preferences_pref.getString("pref_name","pref_name_isNull");
                String profile=preferences_pref.getString("pref_img","pref_img_isNull");
//                ----- api 담을 dto 만들어주기 ------
                BoardWrite boardWrite=new BoardWrite();
                boardWrite.setArticleId(articleId);
                boardWrite.setUserId(userid);
                boardWrite.setProfilePath(profile);
                boardWrite.setUserName(username);
                boardWrite.setContent(binding.boardDetailCommentInput.getText().toString());
//                ----------------------------------

                RetrofitApiSerivce service =RetrofitClient.getInstance().create(RetrofitApiSerivce.class);
                service.writeCommnet(boardWrite).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                        Log.d(TAG, "댓글 작성 서버 전송 boardWrite : "+boardWrite.toString().trim());
                        Log.d(TAG, "댓글 작성 onResponse code:" +response.code());
                        Log.d(TAG, "댓글 작성 res.body: "+response.body());
                        if(response.isSuccessful()){
                            Log.d(TAG, "댓글 작성 isSuccessful.res.code "+response.code());
                            Log.d(TAG, "댓글 작성 서버 전송 성공");
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        t.printStackTrace();
                        Log.d(TAG, "댓글 작성 서버 전송 실패 ");
                    }
                });

                //               댓글창 비워주기
                binding.boardDetailCommentInput.setText("");

                // 댓글 작성시 자기 자신으로 다시 오기 ( 리로딩 )
                BoardDetailFragment boardDetailFragment=new BoardDetailFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .detach(boardDetailFragment).attach(boardDetailFragment).commit();

            }
        });

//      ---------- 게시글 댓글 작성  로직 끝----------------

        ViewGroup view =binding.getRoot();
        return view;
    }

    // 이 프래그먼트에서 넘어갈 때 다시 바텀 네비 활성화
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        hideBottomNavigation(false);
//       ---- 댓글 위해서 받아놓은 article id 없애기 , 다음 요청에서 겹치면 안되기때문에 -----
        SharedPreferences preferences= getActivity().getSharedPreferences("board",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.clear();
        editor.commit();
//      ---------------------------------------------------------------------
    }

    //바텀 네비 숨기기 로직
    public void hideBottomNavigation(Boolean bool) {
        BottomNavigationView bottomNavigation = getActivity().findViewById(R.id.nav_view);
        if (bool == true)
            bottomNavigation.setVisibility(View.GONE);
        else
            bottomNavigation.setVisibility(View.VISIBLE);
    }

    public String DateToString(String s){
        StringBuilder sb =new StringBuilder();
        for(int i=0;i<16;i++){
            char c =s.charAt(i);
            if(!(0<=c-'0' && c-'0'<=9)){
                if(c=='-'){
                    sb.append('.');
                }else if(c==':'){
                    sb.append(':');
                } else {
                    sb.append(" ");
                }
            }else {
                sb.append(c);
            }
        }
        return sb.toString().trim();
    }

//    ---- 뒤로가기 버튼 로직 (몸통은 메인액티비티에 ) -------

    @Override
    public void onBack() {
        BoardMainFragment fragment =new BoardMainFragment();
        Log.d(TAG, " 뒤로가기 버튼 실행  ");
        SharedPreferences preferences= getActivity().getSharedPreferences("board",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =preferences.edit();
        editor.putString("region",region);
        editor.commit();
        //리스너를 설정하기 위해 Activity 받아오기
        MainActivity activity = (MainActivity)getActivity();
        // 한번 뒤로가기 버튼을 눌렀다면 Listener를 null 로 해제
        activity.setOnBackPressedListener(null);
        // 보드 메인으로 이동
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_activity,fragment).commit();
    }

//   Fragment 호출 시 반드시 호출되는 오바라이드 메소드
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((MainActivity)context).setOnBackPressedListener(this);
    }
}

