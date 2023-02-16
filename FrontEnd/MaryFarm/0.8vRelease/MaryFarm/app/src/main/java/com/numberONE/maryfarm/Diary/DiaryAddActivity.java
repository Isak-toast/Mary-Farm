package com.numberONE.maryfarm.Diary;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.numberONE.maryfarm.MainActivity;
import com.numberONE.maryfarm.R;
import com.numberONE.maryfarm.Retrofit.RetrofitApiSerivce;
import com.numberONE.maryfarm.Retrofit.RetrofitClient;
import com.numberONE.maryfarm.databinding.ActivityDiaryAddBinding;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class DiaryAddActivity extends AppCompatActivity {

    private static final String TAG="AddActivity";
    ActivityDiaryAddBinding binding;
    String filePath;
    Bitmap ImgPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDiaryAddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //카메라
        ActivityResultLauncher<Intent> cameraFileLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        int calRatio = calculateInSampleSize(
                                Uri.fromFile(new File(filePath)),
                                getResources().getDimensionPixelSize(R.dimen.imgSize),
                                getResources().getDimensionPixelSize(R.dimen.imgSize)
                        );

                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inSampleSize = calRatio;
                        Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);
                        if (bitmap != null) {
                            ImgPath=bitmap; // 작성 버튼 클릭시 넘겨주기 위해
                            binding.Image.setImageBitmap(bitmap);
                        }
                    }
                }
        );
        // 카메라
        binding.CameraBtn.setOnClickListener(view -> {
            try {
                // 현재시간을 기준으로 파일명 생성
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                File file = File.createTempFile(
                        "JPEG" + timeStamp + "_",
                        ".jpg",
                        storageDir
                );
                filePath = file.getAbsolutePath();

                //fileprovider를 이용해서 외부에 공개 ,mainfest에 지정한 authority와 일치하게 작성
                Uri photoURI = FileProvider.getUriForFile(
                        this,
                        "com.numberONE.maryfarm.fileprovider",
                        file
                );

                if(binding.CameraBtn.getVisibility() == View.VISIBLE){
                    binding.GalleryBtn.setVisibility(View.GONE);
                    binding.CameraBtn.setVisibility(View.GONE);
                    binding.ImageSpinner.setVisibility(View.VISIBLE);
                }

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                cameraFileLauncher.launch(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        // 갤러리와 연동
        ActivityResultLauncher<Intent> galleryLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getData() != null) {
                        int calRatio = calculateInSampleSize(
                                result.getData().getData(),
                                getResources().getDimensionPixelSize(R.dimen.imgSize),
                                getResources().getDimensionPixelSize(R.dimen.imgSize)
                        );

                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inSampleSize = calRatio;

                        try {
                            // 사진 읽어오기
                            InputStream inputStream =getContentResolver().openInputStream(result.getData().getData());
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, options);
                            if (bitmap != null) {
                                ImgPath = bitmap; // 작성 버튼 클릭시 넘겨주기 위해
                                binding.Image.setImageBitmap(bitmap);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

        //갤러리
        binding.GalleryBtn.setOnClickListener(view -> {

            try {
                // 현재시간을 기준으로 파일명 생성
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                File file = File.createTempFile(
                        "JPEG" + timeStamp + "_",
                        ".jpg",
                        storageDir
                );
                filePath = file.getAbsolutePath();

                //fileprovider를 이용해서 외부에 공개 ,mainfest에 지정한 authority와 일치하게 작성
                Uri photoURI = FileProvider.getUriForFile(
                        this,
                        "com.numberONE.maryfarm.fileprovider",
                        file
                );

                // 중앙에 카메라,앨범 버튼 사라지고 왼쪽 상단에 스피너 띄우기
                if(binding.GalleryBtn.getVisibility() == View.VISIBLE){
                    binding.GalleryBtn.setVisibility(View.GONE);
                    binding.CameraBtn.setVisibility(View.GONE);
                    binding.ImageSpinner.setVisibility(View.VISIBLE);
                }

                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI); // 갤러리 지정
                intent.setType("image/*");
                galleryLauncher.launch(intent);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });


//       사진 선택 후 이미지 위 스피너 ( 앨범 ,카메라 실행 구현 해야함 )
        binding.ImageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).toString().equals("카메라")){
                    try {
                        // 현재시간을 기준으로 파일명 생성
                        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                        File file = File.createTempFile(
                                "JPEG" + timeStamp + "_",
                                ".jpg",
                                storageDir
                        );
                        filePath = file.getAbsolutePath();

                        //fileprovider를 이용해서 외부에 공개 ,mainfest에 지정한 authority와 일치하게 작성
                        Uri photoURI = FileProvider.getUriForFile(
                                getApplication(),
                                "com.numberONE.maryfarm.fileprovider",
                                file
                        );

                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                        cameraFileLauncher.launch(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }else if(parent.getItemAtPosition(position).toString().equals("갤러리")){
                    try {
                        // 현재시간을 기준으로 파일명 생성
                        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                        File file = File.createTempFile(
                                "JPEG" + timeStamp + "_",
                                ".jpg",
                                storageDir
                        );
                        filePath = file.getAbsolutePath();

                        //fileprovider를 이용해서 외부에 공개 ,mainfest에 지정한 authority와 일치하게 작성
                        Uri photoURI = FileProvider.getUriForFile(
                                getApplication(),
                                "com.numberONE.maryfarm.fileprovider",
                                file
                        );

                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI); // 갤러리 지정
                        intent.setType("image/*");
                        galleryLauncher.launch(intent);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.editBtn.setOnClickListener(view -> {

            //  ---------------- intent 로직 안할 경우 지워도 되는 코드 --------------------------

            SharedPreferences preferences= getSharedPreferences("write", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor =preferences.edit();
            editor.putString("code","100");
            editor.putBoolean("flag",true);
            editor.commit();

            Intent intent=new Intent(this, MainActivity.class);
            startActivity(intent);
        });
//  ---------------- intent 로직 안할 경우 지워도 되는 코드 마지막 줄 -----------------

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