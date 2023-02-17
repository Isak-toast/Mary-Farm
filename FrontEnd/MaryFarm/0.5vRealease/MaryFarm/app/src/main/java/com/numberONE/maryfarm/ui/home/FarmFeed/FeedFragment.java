package com.numberONE.maryfarm.ui.home.FarmFeed;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.numberONE.maryfarm.Retrofit.Diary.DiaryTopRecommend;
import com.numberONE.maryfarm.Retrofit.Diary.UserItem;
import com.numberONE.maryfarm.Retrofit.RetrofitApiSerivce;
import com.numberONE.maryfarm.Retrofit.RetrofitClient;
import com.numberONE.maryfarm.databinding.FragmentFeedBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FeedFragment extends Fragment {

    private static String TAG = "FeedFragment";

    private FragmentFeedBinding binding;
    RecyclerView recyclerView_followers,recyclerView_others;
    RecyclerView.LayoutManager layoutManager_followers,layoutManager_others;
    RecyclerView.Adapter adapter_follower,adapter_others;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFeedBinding.inflate(inflater,container,false);

//        SharedPreferences 에서 데이터 가져오기
//        SharedPreferences preferences = getActivity().getSharedPreferences("user_id", Context.MODE_PRIVATE);
//        String user_id=preferences.getString("user_id",null);

        RetrofitApiSerivce service = RetrofitClient.getInstance().create(RetrofitApiSerivce.class);

        Call<List<UserItem>> follower_call =service.getFollowerFeed("10101010");
        follower_call.enqueue(new Callback<List<UserItem>>() {
            @Override
            public void onResponse(Call<List<UserItem>> call, Response<List<UserItem>> response) {
                Log.d(TAG, " 팔로워 피드 서버 응답 성공  ");
                Log.d(TAG, " 팔로워 피드 res.code : "  + response.code());
                Log.d(TAG, " 팔로워 피드 res body : "+ response.body());
                if(response.isSuccessful()) {
                    followerDataList(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<UserItem>> call, Throwable t) {
                Log.d(TAG, " 팔로워 피드 서버 응답 실패 ");
                Toast.makeText(getActivity(),"상단 피드 오류 발생 ! " ,Toast.LENGTH_SHORT).show();
            }
        });



//        ------------------------------------------------------------



        Call<List<DiaryTopRecommend>> others_call =service.getOthersFeed();
        others_call.enqueue(new Callback<List<DiaryTopRecommend>>() {
            @Override
            public void onResponse(Call<List<DiaryTopRecommend>> call, Response<List<DiaryTopRecommend>> response) {
                Log.d(TAG, " 추천 피드 서버 통신 성공 ");
                Log.d(TAG, " 추천 피드 res.code : "  + response.code());
                Log.d(TAG, "추천 피드 res body : "+ response.body());
                if(response.isSuccessful()) {
                    othersDataList(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<DiaryTopRecommend>> call, Throwable t) {
                Log.d(TAG, " 추천 피드 서버 통신 실패 ");
                t.printStackTrace();
            }
        });

        ViewGroup view = binding.getRoot();
        return view;
    }

    //  팔로워 피드 리사이클러뷰
    private void followerDataList(List<UserItem> list){

        recyclerView_followers=binding.followerFeed;
        layoutManager_followers=new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false);
        recyclerView_followers.setLayoutManager(layoutManager_followers);

        adapter_follower=new FeedFollowersAdapter(getActivity(),list);
        recyclerView_followers.setAdapter(adapter_follower);
    }


    //     추천 피드 리사이클러뷰
    private void othersDataList(List<DiaryTopRecommend> list){

        recyclerView_others=binding.otherFeed;
        layoutManager_others =new LinearLayoutManager(getActivity());
        recyclerView_others.setLayoutManager(layoutManager_others);

        adapter_others=new FeedOthersAdapter(getActivity(),list);
        recyclerView_others.setAdapter(adapter_others);

    }

}