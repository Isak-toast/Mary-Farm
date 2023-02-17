package com.numberONE.maryfarm.Retrofit.Calendar;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface RetrofitService {
    // 캘린더
    // 달력 작물 리스트
    @POST("maryfarm-plant-service/api/plant/month/search")
    Call<List<ItemModel>> getList(@Body CalendarDateModel CalendarDate);
    // 일자별 메모
    @POST("maryfarm-calendar-service/api/calendar/day/search")
    Call<List<MemoModel>> getPlant(@Body CalendarPickModel CalendarPick);
    @Multipart
    @PUT("maryfarm-diary-service/api/diary/modify")
    Call<DiaryModifyModel> setDiary(@Part DiaryModifyModel DiaryModify);

    // 위젯 리스트 정보 받아오기
    @GET("maryfarm-plant-service/api/plant/month/today/{userId}")
    Call<List<ItemModel>> getWidget(@Path("userId") String userId);
}
