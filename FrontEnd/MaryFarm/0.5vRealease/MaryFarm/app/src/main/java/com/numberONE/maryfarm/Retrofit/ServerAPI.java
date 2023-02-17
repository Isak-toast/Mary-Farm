package com.numberONE.maryfarm.Retrofit;

import com.numberONE.maryfarm.Retrofit.dto.DetailDiariesPerPlantView.DetailDiariesPerPlantDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ServerAPI {

    @GET("user/{userId}")
    Call<UserInfo> getUserInfo(@Path("userId") String userId);

    @POST("auth/user/signup")
    Call<Signup> postUserInfo(@Body Signup signup);

    @GET("diary/user/{userId}")
    Call<List<UserPlant>> getUserPlant(@Path("userId") String userId);

    @GET("diary/group/{plantId}/")
    Call<DetailDiariesPerPlantDTO> getDiaries(@Path("plantId") String plantId);

    @GET("user/farm/{userId}/")
    Call<FollowFollowing> getFollowInfo(@Path("userId") String userId);
}

