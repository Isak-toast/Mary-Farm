package com.numberONE.maryfarm.Pick;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.numberONE.maryfarm.R;

import java.util.ArrayList;
import java.util.List;

public class PickActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick);

        // 추천 식물 리스트 리싸이클러뷰 출력
        RecyclerView recyclerView = findViewById(R.id.pick_one_list);

        List<PickItem> items = new ArrayList<PickItem>();
        items.add(new PickItem(R.drawable.pick_sample1, "봉선화"));
        items.add(new PickItem(R.drawable.pick_sample2, "물망초"));
        items.add(new PickItem(R.drawable.pick_sample3, "복숭아"));


        LinearLayoutManager linearManager = new LinearLayoutManager(this);
        linearManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(linearManager);
        recyclerView.setAdapter(new PickAdapter(getApplicationContext(),items));    }
}
