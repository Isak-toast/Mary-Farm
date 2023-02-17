package com.numberONE.maryfarm.Retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    //private static final String BASE_URL ="https://maryfarm.shop/";
    //private static final String BASE_URL ="http://192.168.31.244:8000/";
    private static final String BASE_URL ="https://985e5bce-3b72-4068-8079-d7591e5374c9.mock.pstmn.io/";
    private static Retrofit retrofit;

    public static Retrofit getInstance() {
        Gson gson = new GsonBuilder().setLenient().create();
        if (retrofit == null) {
                    retrofit = new Retrofit.Builder() // 객체 생성
                    .baseUrl(BASE_URL) // 서버 url 설정
                    .addConverterFactory(new NullOnEmptyConverterFactory())
                    .addConverterFactory(GsonConverterFactory.create(gson)) // 데이터 파싱 설정 (Gson)
                    .build(); // 통신하여 데이터를 파싱한 retrofit 객체 생성 완료
        }
        return retrofit;
    }

    //response 응답값 아무것도 안 주는 경우 처리
    static class NullOnEmptyConverterFactory extends Converter.Factory {
        @Override
        public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit)
        {
            final Converter<ResponseBody, ?> delegate = retrofit.nextResponseBodyConverter(this, type, annotations);
            return (Converter<ResponseBody, Object>) body -> {
                if (body.contentLength() == 0) {
                    return null;
                }
                return delegate.convert(body);
            };
        }
    }

}
