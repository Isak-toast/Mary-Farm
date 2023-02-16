package com.numberONE.maryfarm.ui.AlgorithmPage.sample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.numberONE.maryfarm.MainActivity;
import com.numberONE.maryfarm.R;

public class RecommendSample2 extends AppCompatActivity{
    private static final String TAG="RecommendSample2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_recommend_example2);

        Button btn =(Button) findViewById(R.id.recommend_end_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
}