package com.numberONE.maryfarm.Retrofit.Chat;

import com.numberONE.maryfarm.Retrofit.Calendar.RetrofitService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitChatFactory {
    private static String BASE_URL="http://i8b308.p.ssafy.io:8000/";

    public static RetrofitChatService create() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(RetrofitChatService.class);
    }
}
