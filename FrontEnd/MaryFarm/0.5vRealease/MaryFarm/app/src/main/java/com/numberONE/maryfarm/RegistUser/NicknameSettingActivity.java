package com.numberONE.maryfarm.RegistUser;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.numberONE.maryfarm.databinding.ActivityNicknameSettingBinding;


public class NicknameSettingActivity extends AppCompatActivity {

    ActivityNicknameSettingBinding binding;
    static String TAG ="닉네임 설정 Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNicknameSettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 회원가입 시 아이디 값 확인용
        Intent intent =getIntent();
        String param = intent.getStringExtra("user_id");
        Log.d(TAG, "onCreate: "+param);
        binding.userId.setText(param);

        // 중복확인 버튼 클릭 시 (db에 있는 닉네임들과 비교)
        // 사용 가능하면 버튼 테두리 색 변경 + 사용가능 멘트 + 아래 (regist_btn,regist_comment) visible로 변경
        binding.checkBtn.setOnClickListener(view->{

        });
    }
}