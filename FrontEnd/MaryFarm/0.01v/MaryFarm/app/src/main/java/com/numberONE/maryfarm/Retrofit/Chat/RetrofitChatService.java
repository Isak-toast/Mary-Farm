package com.numberONE.maryfarm.Retrofit.Chat;

import com.numberONE.maryfarm.Retrofit.Calendar.CalendarDateModel;
import com.numberONE.maryfarm.Retrofit.Calendar.CalendarPickModel;
import com.numberONE.maryfarm.Retrofit.Calendar.DiaryModifyModel;
import com.numberONE.maryfarm.Retrofit.Calendar.ItemModel;
import com.numberONE.maryfarm.Retrofit.Calendar.MemoModel;
import com.numberONE.maryfarm.Retrofit.dto.RoomListView.RoomListDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface RetrofitChatService {
    @GET("maryfarm-chat-service/api/room/search/{userId}")
    Call<RoomListDTO> getChat(@Path("userId") String userId);

}

