package com.numberONE.maryfarm.Retrofit.Calendar;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {
//    private static String BASE_URL="http://54.180.86.161:8080/api/";
//    private static String BASE_URL="https://4b53fbdd-647e-4269-82ab-6c7ddee7f7ad.mock.pstmn.io/";
//    private static String BASE_URL="http://i8b308.p.ssafy.io:8000/";
    private static String BASE_URL="http://192.168.31.244:8000/";

    public static RetrofitService create() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(RetrofitService.class);
    }
}
