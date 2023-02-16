package com.numberONE.maryfarm.KakaoLogin;

import android.app.Application;

import com.kakao.sdk.common.KakaoSdk;
import com.numberONE.maryfarm.BuildConfig;

public class KakaoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        KakaoSdk.init(this,BuildConfig.kakao_key);
//  BuildConfig - java(generated)폴더 안에 있는 com.numberONE.maryfarm의 BuildConfig 클래스와 연결
    }
}
