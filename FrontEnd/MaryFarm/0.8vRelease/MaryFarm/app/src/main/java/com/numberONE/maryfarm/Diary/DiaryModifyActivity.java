package com.numberONE.maryfarm.Diary;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.FileProvider;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.numberONE.maryfarm.MainActivity;
import com.numberONE.maryfarm.R;
import com.numberONE.maryfarm.Retrofit.Calendar.DiaryModifyModel;
import com.numberONE.maryfarm.Retrofit.Calendar.ItemModel;
import com.numberONE.maryfarm.Retrofit.Calendar.RetrofitFactory;
import com.numberONE.maryfarm.Retrofit.Calendar.RetrofitService;
import com.numberONE.maryfarm.databinding.ActivityDiaryModifyBinding;
import com.numberONE.maryfarm.ui.board.BoardMainFragment;
import com.numberONE.maryfarm.ui.home.Calendar.CalendarPlantsAdapter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiaryModifyActivity extends AppCompatActivity {

    String filePath;
    Bitmap ImgPath;
    //Drawer 조절용 토글 버튼 객체 생성
    ActionBarDrawerToggle barDrawerToggle;
    SearchView searchView;

    ActivityDiaryModifyBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDiaryModifyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //상단 메뉴 배경색 지정
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFFFF));
        //하단 메뉴
        BottomNavigationView navView = findViewById(R.id.nav_view);
//        navView.setFocusable(false);
//        navView.setOnItemSelectedListener(new AdapterView.OnItemClickListener());
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.menu_bottom_home, R.id.menu_bottom_chat, R.id.menu_bottom_write, R.id.menu_bottom_alarm, R.id.menu_bottom_farm)
//                .build();

//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(binding.navView, navController);

        // 상단 로고 위치 지정이 안 됌
//        getSupportActionBar().setIcon(R.drawable.logo_icon);
//        getSupportActionBar().setDisplayUseLogoEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);   //홈 아이콘을 숨김처리합니다.
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //액션바 아이콘을 업 네비게이션 형태로 표시합니다.
        getSupportActionBar().setDisplayShowTitleEnabled(false); //액션바에 표시되는 제목의 표시유무를 설정합니다.

        //item icon 색조를 적용하지 않도록 , 이 설정 없을경우 item icon 전부 회색
        binding.drawerNav.setItemIconTintList(null);

//        drawerlayout
        binding.drawerNav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.hamburger_1:
                        Toast.makeText(DiaryModifyActivity.this,"이장님 말씀", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.hamburger_2:
                        Toast.makeText(DiaryModifyActivity.this,"텃밭학교 ", Toast.LENGTH_SHORT).show();
                        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
                        BoardMainFragment boardFragment=new BoardMainFragment();
                        ft.replace(R.id.main_activity,boardFragment);
                        ft.commit();
                        break;
                    case R.id.hamburger_3:
                        Toast.makeText(DiaryModifyActivity.this,"마을회관", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.hamburger_4:
                        Toast.makeText(DiaryModifyActivity.this,"직거래 장터", Toast.LENGTH_SHORT).show();
                        break;
                }

                //클릭시 drawer 닫기
                binding.drawerLayout.closeDrawer(binding.drawerNav);

                return false;
            }
        });

        barDrawerToggle=new ActionBarDrawerToggle(this,binding.drawerLayout, R.string.app_name, R.string.app_name);
        //ActionBar( 제목줄)의 홈 or 업버튼의 위치에 토글아이콘 보이게
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        //햄버거 모양으로 보이도록 토글버튼 동기 맞추기
//        barDrawerToggle.syncState();
//        //햄버거 아이콘과 화살표 아이콘이 자동으로 변환환하도록
//        binding.drawerLayout.addDrawerListener(barDrawerToggle);
//drawerlayout 끝


        // 디테일 페이지에서 넘겨준 데이터 받기
        Intent detailintent = getIntent();
        String diarytitle = detailintent.getStringExtra("diaryTitle");
        String diarycontent = detailintent.getStringExtra("diaryContent");

//        Bitmap diaryimg = detailintent.getParcelableExtra("diaryImage");
        byte[] imgbytes = detailintent.getByteArrayExtra("diaryImage");
        Bitmap diaryimg = BitmapFactory.decodeByteArray(imgbytes, 0, imgbytes.length);

        // 제목, 작물종류 변경
        binding.diaryModifyTitle.setText(diarytitle);
        binding.editTextBox.setText(diarycontent);
        binding.diaryPhotoImage.setImageBitmap(diaryimg);

        //카메라 실행 > 결과값 게시
        ActivityResultLauncher<Intent> cameraFileLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>(){
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        int calRatio = calculateInSampleSize(
                                Uri.fromFile(new File((filePath))),
                                getResources().getDimensionPixelSize(R.dimen.imgSize),
                                getResources().getDimensionPixelSize(R.dimen.imgSize)
                        );

                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inSampleSize = calRatio;
                        Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);
                        if(bitmap != null){
                            ImgPath=bitmap;
                            binding.diaryPhotoImage.setImageBitmap(bitmap);
                        }
                    }
                }
        );

        binding.photoBtn.setOnClickListener(view -> {
            try {
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                File file = File.createTempFile(
                        "JPEG_"+timeStamp+"_",
                        ".jpg",
                        storageDir
                );
                filePath = file.getAbsolutePath();
                Uri photoURI = FileProvider.getUriForFile(
                        this,
                        "com.numberONE.maryfarm.fileprovider",
                        file
                );
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                cameraFileLauncher.launch(intent);
            }catch (Exception e){
                e.printStackTrace();
            }

        });

        // 갤러리 실행 > 결과값 게시
        ActivityResultLauncher<Intent> galleryLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>(){
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        int calRatio = calculateInSampleSize(
                                result.getData().getData(),
                                getResources().getDimensionPixelSize(R.dimen.imgSize),
                                getResources().getDimensionPixelSize(R.dimen.imgSize)
                        );

                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inSampleSize = calRatio;
                        try{
                            InputStream inputStream = getContentResolver().openInputStream(result.getData().getData());
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, options);
                            // inputStream.close();
                            if(bitmap != null){
                                ImgPath=bitmap;
                                binding.diaryPhotoImage.setImageBitmap(bitmap);
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });

        binding.galleryBtn.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            galleryLauncher.launch(intent);
        });

        // 저장 버튼 클릭 -> DB에 정보를 저장하고 이동
        binding.saveBtn.setOnClickListener(view -> {
            // 비트맵 put할 때 40kb넘어가면 오류생겨서 byte배열로 압축해서 넘겨주기
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ImgPath.compress(Bitmap.CompressFormat.PNG,100,stream);
//            byte[] bytes= stream.toByteArray();
            long now = System.currentTimeMillis();
            Date date = new Date(now);

            DiaryModifyModel diaryModify = null;
            diaryModify.diaryContent = binding.editTextBox.getText().toString();
            diaryModify.newimage = ImgPath;
            RetrofitService networkService = RetrofitFactory.create();
            networkService.setDiary(diaryModify)
                    .enqueue(new Callback<DiaryModifyModel>() {
                        @Override
                        public void onResponse(Call<DiaryModifyModel> call, Response<DiaryModifyModel> response){
                            if(response.isSuccessful()){
                                Log.i(TAG, "onResponse: 서버와 연결");

                                // 디테일 페이지로 이동 , this가 안 먹는데?????????????????????????????????????????????????/
                                Intent intent = new Intent(getBaseContext(), DiaryDetailActivity.class);
                                startActivity(intent);
                                Log.d("onCreate: ","데이터 전송완료" );
                            }
                        }
                        @Override
                        public void onFailure(Call<DiaryModifyModel> call, Throwable t){
                            Log.e(TAG, "onFailure: 서버 연결 실패");
                            Log.e(TAG, "onFailure:", t);
                        }
                    });
        });
    }
    //액션바의 메뉴를 클릭하는 이벤트를 듣는 메소드를 통해 클릭 상황 전달
    //토글 버튼이 클릭 상황을 인지하도록
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        barDrawerToggle.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }
    // 상단메뉴 다른 버튼
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        barDrawerToggle=new ActionBarDrawerToggle(this,binding.drawerLayout, R.string.app_name, R.string.app_name);
        getMenuInflater().inflate(R.menu.menu_top, menu);
        return true;
    }
    // 검색 버튼 로직 만들기
    SearchView.OnQueryTextListener queryTextListener =new SearchView.OnQueryTextListener() {
        @Override // 최종 검색을 위해 제출버튼 눌렀을 때
        public boolean onQueryTextSubmit(String query) {
            searchView.setQuery("",false);
            searchView.setIconified(true);
            Toast t = Toast.makeText(DiaryModifyActivity.this,query,Toast.LENGTH_SHORT);
            t.show();
            return false;
        }

        @Override // 유저가 한글자 한글자 입력할 때
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };
    // 키보드 이외의 곳 터치할 경우, 키보드 사라지게하기
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            view.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + view.getLeft() - scrcoords[0];
            float y = ev.getRawY() + view.getTop() - scrcoords[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
    }
    private int calculateInSampleSize(Uri fileUri, int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        try {
            InputStream inputStream = getContentResolver().openInputStream(fileUri);

            //inJustDecodeBounds 값을 true 로 설정한 상태에서 decodeXXX() 를 호출.
            //로딩 하고자 하는 이미지의 각종 정보가 options 에 설정 된다.
            BitmapFactory.decodeStream(inputStream, null, options);
            inputStream.close();
            inputStream = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        //비율 계산........................
        int width = options.outWidth;
        int height = options.outHeight;
        int inSampleSize = 1;

        //inSampleSize 비율 계산
        if (height > reqHeight || width > reqWidth) {

            int halfHeight = height / 2;
            int halfWidth = width / 2;

            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }
}
