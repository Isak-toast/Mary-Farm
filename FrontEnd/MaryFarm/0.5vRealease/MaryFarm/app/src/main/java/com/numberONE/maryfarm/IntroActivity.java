package com.numberONE.maryfarm;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class IntroActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro); //xml , java 소스 연결

        ImageView loading_gif_img = (ImageView)findViewById(R.id.loadingImg);
        Glide.with(this).load(R.raw.loading).into(loading_gif_img);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent intent = new Intent (getApplicationContext(), MainActivity.class);
                startActivity(intent); //인트로 실행 후 바로 MainActivity로 넘어감.
                finish();
            }
        },4000); //4초 후 인트로 실행
    }

    @Override
    protected void onPause(){
        super.onPause();
        finish();
    }
}