package com.numberONE.maryfarm.Diary;

import static com.numberONE.maryfarm.R.*;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.numberONE.maryfarm.Retrofit.AddComment;
import com.numberONE.maryfarm.Retrofit.Comments;
import com.numberONE.maryfarm.Retrofit.DetailsAPI;
import com.numberONE.maryfarm.Pick.PickActivity;
import com.numberONE.maryfarm.R;
import com.numberONE.maryfarm.Retrofit.ServerAPI;
import com.numberONE.maryfarm.Retrofit.dto.DetailDiariesPerPlantView.DetailDiariesPerPlantDTO;
import com.numberONE.maryfarm.Retrofit.dto.DetailDiariesPerPlantView.DetailDiaryCommentDTO;
import com.numberONE.maryfarm.Retrofit.dto.DetailDiariesPerPlantView.DetailDiaryDTO;
import com.numberONE.maryfarm.databinding.ActivityDiaryDetailBinding;
import com.numberONE.maryfarm.ui.board.BoardMainFragment;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.HEAD;

public class DiaryDetailActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{
    private String URL = "https://s3.ap-northeast-2.amazonaws.com/maryfarm.bucket/";
    // ????????? ??????
    private boolean sign=false;
    private TextView likeCount;
    // Intent??? ?????? ????????? ??????
    public TextView title;
    public TextView diaryContent;
    public Bitmap diaryImage;
    // ???????????? ??????
    ActionBarDrawerToggle barDrawerToggle;
    SearchView searchView;
    private int likeCnt;
    private String commentContent;

    // ?????? ????????? ?????? (?????? ????????????, ????????????, ???????????? ??????)
    ImageButton popUpBtn;

    ActivityDiaryDetailBinding binding;
    // sharedpreference practice
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    String userId, userImg;
    TextView textView;

    TextView contentView, likesView, titleView, startView, endView, afterView;
    ImageView profileImgView, detailImageView, commentImgView;
    EditText nicknameView, commentContentView;
    Button commentAddView;
    ImageButton nextBtnView, formBtnView;
    static int idx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_diary_detail);
        binding = ActivityDiaryDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        DetailDiariesPerPlantDTO plantAllInfo = (DetailDiariesPerPlantDTO) intent.getSerializableExtra("detailDiariesPerPlantDTO");

        //?????? ?????? ????????? ??????
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFFFF));
        //???????????? ???????????? ????????? ??????????????? ??????
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //item icon ????????? ???????????? ????????? , ??? ?????? ???????????? item icon ?????? ??????
        binding.drawerNav.setItemIconTintList(null);
//drawerlayout
        binding.drawerNav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.hamburger_1:
                        Toast.makeText(DiaryDetailActivity.this,"????????? ??????", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.hamburger_2:
                        Toast.makeText(DiaryDetailActivity.this,"???????????? ", Toast.LENGTH_SHORT).show();
                        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
                        BoardMainFragment boardFragment=new BoardMainFragment();
                        ft.replace(R.id.main_activity,boardFragment);
                        ft.commit();
                        break;
                    case R.id.hamburger_3:
                        Toast.makeText(DiaryDetailActivity.this,"????????????", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.hamburger_4:
                        Toast.makeText(DiaryDetailActivity.this,"????????? ??????", Toast.LENGTH_SHORT).show();
                        break;
                }

                //????????? drawer ??????
                binding.drawerLayout.closeDrawer(binding.drawerNav);

                return false;
            }
        });

        barDrawerToggle=new ActionBarDrawerToggle(this,binding.drawerLayout, R.string.app_name, R.string.app_name);

        // ?????? ???????????? ?????? ?????????
//        title = binding.title;
//        diaryContent = binding.diaryContent;
//        BitmapDrawable diaryimg = (BitmapDrawable) binding.diaryDetailImage.getDrawable();
//        diaryImage = diaryimg.getBitmap();

        String profileImg = plantAllInfo.getProfilePath();
        profileImgView = findViewById(R.id.profileImg);
        Glide.with(DiaryDetailActivity.this).load(URL + profileImg).into(profileImgView);

        String title = plantAllInfo.getTitle();
        titleView = findViewById(id.title);
        titleView.setText(title);


        startView = findViewById(id.startDate);
        startView.setText(plantAllInfo.getPlantCreatedDate().substring(0,10));

        TextView endDate = findViewById(id.endDate);
        if (plantAllInfo.getHarvestDate() == null){
            endDate.setText("ing");
        } else {
            endDate.setText(plantAllInfo.getHarvestDate().substring(0,10));
        }

        List<DetailDiaryDTO> diaries = plantAllInfo.getDiaries();

        idx = diaries.size()-1;

        detailImageView = findViewById(id.diaryDetailImage);
        Glide.with(DiaryDetailActivity.this).load(URL + diaries.get(idx).getImagePath()).into(detailImageView);

        contentView = findViewById(R.id.diaryContent);
        contentView.setText(diaries.get(idx).getContent());

        likesView = findViewById(id.like_Count);
        likesView.setText(diaries.get(idx).getLikes()+"");
        likeCnt = diaries.get(idx).getLikes();

        RecyclerView recyclerView = findViewById(id.commentsView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<DetailDiaryCommentDTO> callComments = diaries.get(idx).getComments();
        if (callComments != null){
            recyclerView.setAdapter(new CommentAdapter(getApplicationContext(), callComments));
        }

        ImageButton nextBtn = (ImageButton) findViewById(id.nextBtn);
        ImageButton formerBtn = (ImageButton) findViewById(id.formerBtn);

        if (idx == diaries.size()-1){
            formerBtn.setVisibility(View.GONE);
        }

        if (idx == 0){
            nextBtn.setVisibility(View.GONE);
        }


        // sharedpreference practice
        pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        userId = pref.getString("userNickname", "Null");
        userImg = pref.getString("userImg", "Null");

        commentImgView = findViewById(id.commentProfile);
        Glide.with(DiaryDetailActivity.this).load(URL + userImg).into(commentImgView);

        nicknameView = findViewById(id.inputComment);
        nicknameView.setHint(userId + "(???)??? ?????? ??????...");

        // ????????? - ????????? & ?????? ??????
        binding.emptyHeartIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!sign) {
                    binding.emptyHeartIcon.setVisibility(View.GONE);
                    binding.fullHeartIcon.setVisibility(View.VISIBLE);
                    sign = true;
                    likeCnt++;
                    binding.likeCount.setText(""+likeCnt);
                }
            }
        });

        // ????????? - ????????? ?????? & ?????? ??????
        binding.fullHeartIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sign) {
                    binding.emptyHeartIcon.setVisibility(View.VISIBLE);
                    binding.fullHeartIcon.setVisibility(View.GONE);
                    sign = false;
                    likeCnt--;
                    binding.likeCount.setText(""+likeCnt);
                }
            }
        });

        // ?????? ?????? ?????????, ?????? ???????????? ?????? ??????
        ImageButton pickBtn = (ImageButton) findViewById(id.pickBtn);
        pickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DiaryDetailActivity.this, PickActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // ?????? ?????? ?????????, ?????? ?????? ???????????? ?????? ??????
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idx = idx - 1;

                detailImageView = findViewById(id.diaryDetailImage);
                Glide.with(DiaryDetailActivity.this).load(URL + diaries.get(idx).getImagePath()).into(detailImageView);

                contentView = findViewById(R.id.diaryContent);
                contentView.setText(diaries.get(idx).getContent());

                likesView = findViewById(id.like_Count);
                likesView.setText(diaries.get(idx).getLikes()+"");
                likeCnt = diaries.get(idx).getLikes();

                formerBtn.setVisibility(View.VISIBLE);
                List<DetailDiaryCommentDTO> callComments = diaries.get(idx).getComments();
                if (callComments != null){
                    recyclerView.setAdapter(new CommentAdapter(getApplicationContext(), callComments));
                }

                if (idx == diaries.size()-1){
                    formerBtn.setVisibility(View.GONE);
                    nextBtn.setVisibility(View.VISIBLE);
                }

                if (idx == 0){
                    formerBtn.setVisibility(View.VISIBLE);
                    nextBtn.setVisibility(View.GONE);
                }

                //commentImgView = findViewById(id.commentProfile);
                //Glide.with(DiaryDetailActivity.this).load(userImg).into(commentImgView);

                //nicknameView = findViewById(id.inputComment);
                //nicknameView.setHint(userId + "(???)??? ?????? ??????...");
            }
        });

        // ?????? ?????? ?????????, ?????? ?????? ???????????? ?????? ??????
        formerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idx = idx + 1;

                detailImageView = findViewById(id.diaryDetailImage);
                Glide.with(DiaryDetailActivity.this).load(URL + diaries.get(idx).getImagePath()).into(detailImageView);

                contentView = findViewById(R.id.diaryContent);
                contentView.setText(diaries.get(idx).getContent());

                likesView = findViewById(id.like_Count);
                likesView.setText(diaries.get(idx).getLikes()+"");
                likeCnt = diaries.get(idx).getLikes();

                List<DetailDiaryCommentDTO> callComments = diaries.get(idx).getComments();
                if (callComments != null){
                    recyclerView.setAdapter(new CommentAdapter(getApplicationContext(), callComments));
                }

                if (idx == diaries.size()-1){
                    formerBtn.setVisibility(View.GONE);
                    nextBtn.setVisibility(View.VISIBLE);
                }

                if (idx == 0){
                    formerBtn.setVisibility(View.VISIBLE);
                    nextBtn.setVisibility(View.GONE);
                }

                //commentImgView = findViewById(id.commentProfile);
                //Glide.with(DiaryDetailActivity.this).load(URL + userImg).into(commentImgView);

                //nicknameView = findViewById(id.inputComment);
                //nicknameView.setHint(userId + "(???)??? ?????? ??????...");
            }
        });

        // "??????" ?????? ?????????, ?????? ??????
        commentAddView = findViewById(R.id.commentAddBtn);
        commentContentView = findViewById(R.id.inputComment);
        // ?????? ?????? = ?????????
        commentAddView.setEnabled(false);
        // ????????? ????????? ?????????, ???????????? ??????
        commentContentView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                commentContent = commentContentView.getText().toString();
                if (commentContent.length() == 0){
                    commentAddView.setEnabled(false);
                } else {
                    commentAddView.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        commentAddView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String diaryId, userId, userName, content, profilePath;

                diaryId = diaries.get(idx).getDiaryId();
                userId = pref.getString("userId", "Null");
                userName = pref.getString("userNickname", "Null");
                content = commentContentView.getText().toString();
                profilePath = pref.getString("userImg", "Null");

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://maryfarm.shop/maryfarm-plant-service/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ServerAPI serverAPI = retrofit.create(ServerAPI.class);
                AddComment addComment = new AddComment(diaryId, userId, userName, content, profilePath);
                Call<AddComment> call = serverAPI.postComment(addComment);
                call.enqueue(new Callback<AddComment>() {
                    @Override
                    public void onResponse(Call<AddComment> call, Response<AddComment> response) {
                        if(!response.isSuccessful()){
                            return;
                        }
                        finish();//????????? ??????
                        overridePendingTransition(0, 0);//????????? ?????? ?????????
                        Intent intent = getIntent(); //?????????
                        startActivity(intent); //???????????? ??????
                        overridePendingTransition(0, 0);//????????? ?????? ?????????
                    }

                    @Override
                    public void onFailure(Call<AddComment> call, Throwable t) {
                        Log.d("", "onFailure: "+t.toString());
                    }
                });

                Toast.makeText(getApplicationContext(), "???????????? ??? ????????????! ????????????", Toast.LENGTH_LONG).show();
                commentContentView.setText(null);
            }
        });
    }

    // ... ?????? ????????? ?????? ?????? ?????? (?????? ??????, ?????? ??????, ????????????)
    public void showPopBtn(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(menu.menu_diary_detail);
        popupMenu.show();
    }
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case id.editDiary:
                Intent intent = new Intent(DiaryDetailActivity.this, DiaryModifyActivity.class);
                intent.putExtra("diaryTitle", title.getText().toString());
                intent.putExtra("diaryContent", diaryContent.getText().toString());
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                diaryImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] diarybyte = stream.toByteArray();
                intent.putExtra("diaryImage", diarybyte);
                startActivity(intent);
                finish();
                return true;
            case id.addDiary:
                Intent intent1 = new Intent(DiaryDetailActivity.this, DiaryAddActivity.class);
                startActivity(intent1);
                finish();
                return true;
            case id.plantComplete:
                Toast.makeText(this, "???????????? ??? ????????????! ????????????", Toast.LENGTH_LONG).show();
                String koreaNow = LocalDate.now(ZoneId.of("Asia/Seoul")).toString();

                TextView endDate = findViewById(id.endDate);
                endDate.setText(koreaNow);

                return true;
            default:
                return false;
        }
    }

    // ????????? ????????? ??? ????????? ??????, ????????? ??????????????????
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            view.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + view.getLeft() - scrcoords[0];
            float y = ev.getRawY() + view.getTop() - scrcoords[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom()) {
                ((InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
                view.clearFocus();
            }
        }
        return super.dispatchTouchEvent(ev);
    }
    // ???????????? ?????? ??????
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        barDrawerToggle=new ActionBarDrawerToggle(this, binding.drawerLayout, R.string.app_name, R.string.app_name);
        getMenuInflater().inflate(R.menu.menu_top, menu);
        return true;
    }
    // ????????? ?????? ??????
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        barDrawerToggle.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }
    // ?????? ?????? ?????? ?????????
    SearchView.OnQueryTextListener queryTextListener =new SearchView.OnQueryTextListener() {
        @Override // ?????? ????????? ?????? ???????????? ????????? ???
        public boolean onQueryTextSubmit(String query) {
            searchView.setQuery("",false);
            searchView.setIconified(true);
            Toast t = Toast.makeText(DiaryDetailActivity.this,query,Toast.LENGTH_SHORT);
            t.show();
            return false;
        }

        @Override // ????????? ????????? ????????? ????????? ???
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };
}