package com.numberONE.maryfarm.ui.home.FarmFeed;

import android.app.Activity;
import android.content.SharedPreferences;
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
import com.numberONE.maryfarm.Retrofit.Thumbnail;
import com.numberONE.maryfarm.Retrofit.dto.FirstHomeView.FirstHomeViewDTO;
import com.numberONE.maryfarm.Retrofit.dto.FirstHomeView.HomeDiaryImageDTO;
import com.numberONE.maryfarm.Retrofit.dto.FirstHomeView.HomeFollowerImageDTO;
import com.numberONE.maryfarm.databinding.FragmentFeedBinding;

import java.util.ArrayList;
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

        SharedPreferences pref;
        String userId, userNickname, userImage;

        pref = getActivity().getSharedPreferences("pref", Activity.MODE_PRIVATE);
        userId = pref.getString("pref_id", "Null");
        userNickname = pref.getString("pref_name", "Null");
        userImage = pref.getString("pref_img","Null");

        RetrofitApiSerivce service = RetrofitClient.getInstance().create(RetrofitApiSerivce.class);
        Call<FirstHomeViewDTO> follower_call = service.getFollowerFeed(userId);
        follower_call.enqueue(new Callback<FirstHomeViewDTO>() {
            @Override
            public void onResponse(Call<FirstHomeViewDTO> call, Response<FirstHomeViewDTO> response) {
                Log.d(TAG, "onResponse: !!!!!!!!!!!!!!!!!!!"+response.body().getUserId());
                if(response.isSuccessful()) {
                    Log.d(TAG, "onResponse: "+response.body().getFollowers());
                    List<HomeFollowerImageDTO> homeFollowerImageDTO = (List) response.body().getFollowers();
                    followerDataList(homeFollowerImageDTO);

                    List<Thumbnail> planThumbnails =new ArrayList<>();
                    List<HomeDiaryImageDTO> homeDiaryImageDTOS = response.body().getDiaries();
                    for (int j=homeDiaryImageDTOS.size()-1; j>=0; j--){
                        String title = homeDiaryImageDTOS.get(j).getContent();
                        String plantId = homeDiaryImageDTOS.get(j).getPlantId();
                        String thumbImg1 = null;
                        String thumbImg2 = null;
                        String thumbImg3 = null;
                        String plantCreatedDate = homeDiaryImageDTOS.get(j).getCreatedDate();
                        String harvestDate = null;
                        String profilepath = homeDiaryImageDTOS.get(j).getProfilePath();

                        for (int k=homeDiaryImageDTOS.get(j).getOtherDiaryImagePath().size()-1; k>=0; k--){
                            if (k==homeDiaryImageDTOS.get(j).getOtherDiaryImagePath().size()-1){
                                thumbImg3 = homeDiaryImageDTOS.get(j).getOtherDiaryImagePath().get(k);
                            } else if (k==homeDiaryImageDTOS.get(j).getOtherDiaryImagePath().size()-2) {
                                thumbImg2 = homeDiaryImageDTOS.get(j).getOtherDiaryImagePath().get(k);
                            } else if (k==homeDiaryImageDTOS.get(j).getOtherDiaryImagePath().size()-3) {
                                thumbImg1 = homeDiaryImageDTOS.get(j).getOtherDiaryImagePath().get(k);
                            }
                        }
                        Thumbnail thumbnail = new Thumbnail(title, thumbImg1, thumbImg2, thumbImg3, plantId, plantCreatedDate, harvestDate, profilepath, null);
                        planThumbnails.add(thumbnail);
                    }
                    othersDataList(planThumbnails);
                }
            }

            @Override
            public void onFailure(Call<FirstHomeViewDTO> call, Throwable t) {
                Log.d(TAG, "onFailure:"+t.toString());
            }
        });


        ViewGroup view = binding.getRoot();
        return view;
    }

    //  팔로워 피드 리사이클러뷰
    private void followerDataList(List<HomeFollowerImageDTO> list){

        recyclerView_followers=binding.followerFeed;
        layoutManager_followers=new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false);
        recyclerView_followers.setLayoutManager(layoutManager_followers);

        //adapter_follower=new FeedFollowersAdapter(getActivity(),list);
        recyclerView_followers.setAdapter(new FeedFollowersAdapter(list, getContext()));
    }


    //     추천 피드 리사이클러뷰
    private void othersDataList(List<Thumbnail> list){

        recyclerView_others=binding.otherFeed;
        layoutManager_others =new LinearLayoutManager(getActivity());
        recyclerView_others.setLayoutManager(layoutManager_others);

        recyclerView_others.setAdapter(new FeedOthersAdapter(getContext(), list));

    }

}