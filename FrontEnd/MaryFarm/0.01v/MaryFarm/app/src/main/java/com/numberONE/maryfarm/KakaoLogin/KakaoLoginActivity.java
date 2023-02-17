package com.numberONE.maryfarm.KakaoLogin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.kakao.sdk.user.UserApiClient;
import com.numberONE.maryfarm.Retrofit.ServerAPI;
import com.numberONE.maryfarm.Retrofit.Signup;
import com.numberONE.maryfarm.Retrofit.UserInfo;
import com.numberONE.maryfarm.MainActivity;
import com.numberONE.maryfarm.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.HEAD;

public class KakaoLoginActivity extends AppCompatActivity {

    private String user_id; // 사용자 고유번호
    private String user_name; // 이름
    private String user_image; // 프로필 주소 https:// ~~

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kakao_login);
        //상단 메뉴 배경색 지정
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFFFF));
        // 상단 로고
        getSupportActionBar().setIcon(R.drawable.logo_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);  //홈 아이콘을 숨김처리합니다.

        ImageButton kakao_login_button = (ImageButton)findViewById(R.id.kakao_login_btn);
        kakao_login_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                resetSharedPreferences(); // 혹시 몰라서 sharedpreferences 초기화하고 시작
                if(UserApiClient.getInstance().isKakaoTalkLoginAvailable(KakaoLoginActivity.this)){
                    login(); // 회원 인 경우
                }
                else{
                    accountLogin(); // 회원이 아닌 경우
                }
            }
        });
    }

    // 회원인 경우
    public void login(){
        String TAG = "login()";
        UserApiClient.getInstance().loginWithKakaoTalk(KakaoLoginActivity.this,(oAuthToken, error) -> {
            if (error != null) {
                Log.e(TAG, "회원인 경우 - 로그인 실패", error);
            } else if (oAuthToken != null) {
                Log.i(TAG, "회원인 경우 - 로그인 성공(토큰) : " + oAuthToken.getAccessToken());
                getUserInfo();
            }
            return null;
        });
    }

    // 회원이 아닌 경우
    public void accountLogin(){
        String TAG = "accountLogin()";
        UserApiClient.getInstance().loginWithKakaoAccount(KakaoLoginActivity.this,(oAuthToken, error) -> {
            if (error != null) {
                Log.e(TAG, "회원이 아닌 경우 - 로그인 실패", error);
            } else if (oAuthToken != null) {
                Log.i(TAG,  "회원이 아닌 경우 - 로그인 성공(토큰) : " + oAuthToken.getAccessToken());
                getUserInfo();
            }
            return null;
        });
    }

    // 회원 ,비회원 로그인 성공 후 로직
    public void getUserInfo() {
        String TAG = "getUserInfo()";
        UserApiClient.getInstance().me((user, meError) -> {
            if (meError != null) {
                Log.e(TAG, "사용자 정보 요청 실패", meError);
            } else {
                Log.d(TAG, "로그인 성공 후 로직 시작 ! ");
                Log.d(TAG, "getUserInfo:" + user.toString());

//                user_id = user.getId() + " "; // 사용자 고유번호
                user_id = "2626273196"; // 사용자 고유번호

                //user_name = user.getKakaoAccount().getProfile().getNickname(); // 이름
                //user_image = user.getKakaoAccount().getProfile().getProfileImageUrl(); // 프로필 주소 https:// ~~

                // # 1-1. 기회원인지 확인
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://maryfarm.shop/maryfarm-user-service/api/")
//                        .baseUrl("http://192.168.31.244:8000/maryfarm-user-service/api/")
//                        .baseUrl("https://985e5bce-3b72-4068-8079-d7591e5374c9.mock.pstmn.io/api/")
//                        .baseUrl("http://i8b308.p.ssafy.io:8000/maryfarm-user-service/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ServerAPI serverAPI = retrofit.create(ServerAPI.class);
                Call<UserInfo> call = serverAPI.getUserInfo(user_id); // 유저 정보가 DB에 존재하는지 체크

                call.enqueue(new Callback<UserInfo>() {

                    @Override
                    public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                        UserInfo userInfo = response.body();
                        Log.d(TAG, "onResponse: "+userInfo.getUserName());
//                        user_name = userInfo.getUserName(); // 이름
//                        user_image = userInfo.getProfilePath(); // 프로필 주소 https:// ~~

                        user_name = "김차분"; // 이름
                        user_image ="maryfarm/c3054490-8cd3-4965-b335-e70d7fdbc158.jpeg" ; // 프로필 주소 https:// ~~


                        // # 1-2. 기회원이 아닌 경우, Sign up 진행
                        if (userInfo == null) {
                            Retrofit retrofit1 = new Retrofit.Builder()
                                    .baseUrl("https://maryfarm.shop/maryfarm-user-service/api/")
//                                    .baseUrl("http://192.168.31.244:8000/maryfarm-user-service/api/")
//                                    .baseUrl("https://985e5bce-3b72-4068-8079-d7591e5374c9.mock.pstmn.io/api/")
//                                    .baseUrl("http://i8b308.p.ssafy.io:8000/maryfarm-user-service/api/")
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();

                            ServerAPI serverAPI1 = retrofit1.create(ServerAPI.class);

                            Signup signup = new Signup(user_id, user_name);
                            Log.d(TAG, "onResponse: Signup"+ signup.getUserName());
                            // 회원 가입 하기
                            Call<Signup> call1 = serverAPI1.postUserInfo(signup);
                            call1.enqueue(new Callback<Signup>() {
                                @Override
                                public void onResponse(Call<Signup> call1, Response<Signup> response) {
                                    Log.d(TAG, "회원가입 onResponse " + response.body().toString());
                                    Log.d(TAG, "회원가입 성공!" + response.body().getUserName());
                                    addSharedPreferences(user_id, user_name, user_image);
                                    Intent intent = new Intent(KakaoLoginActivity.this, MainActivity.class);
                                    intent.putExtra("user_id", user_id);
                                    startActivity(intent);
                                }

                                @Override
                                public void onFailure(Call<Signup> call1, Throwable t) {
                                    t.printStackTrace();
                                    Log.d(TAG, "Signup 로직 실행 중 실패 ");
                                }
                            });
                        } else { // 이미 회원인 경우
                            Log.d(TAG, "Already Signup!!!!!");
                            addSharedPreferences(user_id, user_name, user_image);
                            Intent intent = new Intent(KakaoLoginActivity.this, MainActivity.class);
                            intent.putExtra("user_id", user_id);
                            startActivity(intent);

                        }

                    }

                    @Override
                    public void onFailure(Call<UserInfo> call, Throwable t) {
                        t.printStackTrace();
                        Log.d(TAG, "유저 디비에 있는 지 체크 중 오류 ");
                    }
                });
            }
            return null;
        });
    }

    // SharedPreferences에 정보 담기
    public void addSharedPreferences(String id,String name, String img){
        SharedPreferences preferences=getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
        editor.putString("pref_id",id);
        editor.putString("pref_name",name);
        editor.putString("pref_img",img); // url 형태인데 문자열로 받음 ( 꺼내쓸 때 참고 )
        editor.apply();
        // commit() : 동기적으로 동작하기 때문에 처리 중인 쓰레드가 blocking 될 수 있습니다.
        //            그렇기 때문에 저장될 때까지 기다릴 필요가 없다면 apply()를 사용하는 것이 좋습니다.
    }


    //shared 비우고 시작
    public void resetSharedPreferences(){
        SharedPreferences preferences=getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();

        SharedPreferences preferences_write=getSharedPreferences("write",Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences_write.edit();
        edit.clear();
        edit.commit();

    }
}
