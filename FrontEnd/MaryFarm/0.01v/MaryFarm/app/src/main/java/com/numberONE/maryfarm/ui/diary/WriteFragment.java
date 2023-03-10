package com.numberONE.maryfarm.ui.diary;

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
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.numberONE.maryfarm.MainActivity;
import com.numberONE.maryfarm.R;
import com.numberONE.maryfarm.Retrofit.Diary.DiaryInit;
import com.numberONE.maryfarm.Retrofit.RetrofitApiSerivce;
import com.numberONE.maryfarm.Retrofit.RetrofitClient;
import com.numberONE.maryfarm.databinding.FragmentWriteBinding;
import com.numberONE.maryfarm.ui.myfarm.MyfarmFragment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class WriteFragment extends Fragment {

    private static final String TAG="WriteFragment";
    FragmentWriteBinding binding;
    String filePath;
    Bitmap ImgPath;
    Uri photoURI;
    String input_title,input_name,input_content;

    public WriteFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWriteBinding.inflate(inflater,container,false);
        Log.d(TAG, "onCreateView: ??????");

        // ????????? ??? ?????? ????????? ????????? ????????????
        binding.writeFragment.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                hideKeyboard();
                return false;
            }
        });

        //?????????
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
                            ImgPath=bitmap; // ?????? ?????? ????????? ???????????? ??????
                            binding.Image.setImageBitmap(bitmap);
                        }
                    }
                }
        );
        // ?????????
        binding.CameraBtn.setOnClickListener(view -> {
            try {
                // ??????????????? ???????????? ????????? ??????
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                File file = File.createTempFile(
                        "JPEG" + timeStamp + "_",
                        ".jpg",
                        storageDir
                );
                filePath = file.getAbsolutePath();

                //fileprovider??? ???????????? ????????? ?????? ,mainfest??? ????????? authority??? ???????????? ??????
                photoURI = FileProvider.getUriForFile(
                        getActivity(),
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
        // ???????????? ??????
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
                            // ?????? ????????????
                            InputStream inputStream = getActivity().getContentResolver().openInputStream(result.getData().getData());
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, options);
                            if (bitmap != null) {
                                ImgPath = bitmap; // ?????? ?????? ????????? ???????????? ??????
                                binding.Image.setImageBitmap(bitmap);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

        //?????????
        binding.GalleryBtn.setOnClickListener(view -> {

            try {
                // ??????????????? ???????????? ????????? ??????
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                File file = File.createTempFile(
                        "file_" + timeStamp + "_",
                        ".jpg",
                        storageDir
                );
                filePath = file.getAbsolutePath();

                //fileprovider??? ???????????? ????????? ?????? ,mainfest??? ????????? authority??? ???????????? ??????
                photoURI = FileProvider.getUriForFile(
                        getActivity(),
                        "com.numberONE.maryfarm.fileprovider",
                        file
                );

                // ????????? ?????????,?????? ?????? ???????????? ?????? ????????? ????????? ?????????
                if(binding.GalleryBtn.getVisibility() == View.VISIBLE){
                    binding.GalleryBtn.setVisibility(View.GONE);
                    binding.CameraBtn.setVisibility(View.GONE);
                    binding.ImageSpinner.setVisibility(View.VISIBLE);
                }

                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI); // ????????? ??????
                intent.setType("image/*");
                galleryLauncher.launch(intent);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

//       ?????? ?????? ??? ????????? ??? ?????????
        binding.ImageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).toString().equals("?????????")){
                    try {
                        // ??????????????? ???????????? ????????? ??????
                        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                        File file = File.createTempFile(
                                "file_"+ timeStamp + "_",
                                ".jpg",
                                storageDir
                        );
                        filePath = file.getAbsolutePath();

                        //fileprovider??? ???????????? ????????? ?????? ,mainfest??? ????????? authority??? ???????????? ??????
                        photoURI = FileProvider.getUriForFile(
                                getActivity(),
                                "com.numberONE.maryfarm.fileprovider",
                                file
                        );

                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                        cameraFileLauncher.launch(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }else if(parent.getItemAtPosition(position).toString().equals("?????????")){
                    try {
                        // ??????????????? ???????????? ????????? ??????
                        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                        File file = File.createTempFile(
                                "file_" + timeStamp + "_",
                                ".jpg",
                                storageDir
                        );
                        filePath = file.getAbsolutePath();

                        //fileprovider??? ???????????? ????????? ?????? ,mainfest??? ????????? authority??? ???????????? ??????
                        photoURI = FileProvider.getUriForFile(
                                getActivity(),
                                "com.numberONE.maryfarm.fileprovider",
                                file
                        );

                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI); // ????????? ??????
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


        binding.writeBtn.setOnClickListener(view -> {


//  ---------------- intent ?????? ?????? ?????? ????????? ?????? ?????? --------------------------

            SharedPreferences preferences= getActivity().getSharedPreferences("write",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor =preferences.edit();
            editor.putString("code","100");
            editor.putBoolean("flag",true);
            editor.commit();

            Intent intent=new Intent(getActivity(),MainActivity.class);
            startActivity(intent);
        });
//  ---------------- intent ?????? ?????? ?????? ????????? ?????? ?????? ????????? ??? -----------------


////          --- ?????? ?????? ??????  ?????? --------------------------
////            input_title=binding.title.getText().toString();
////            input_name=binding.plantsTypeSpinner.getSelectedItem().toString();
////            input_content=binding.content.getText().toString();
////
////            Log.d(TAG, "input_title_  " + input_title );
////            Log.d(TAG, "input_plant_option  " + input_name );
////            Log.d(TAG, "input_content  " + input_content );
////
////            // ????????? put??? ??? 40kb???????????? ??????????????? byte????????? ???????????? ????????????
////            ByteArrayOutputStream stream = new ByteArrayOutputStream();
////            ImgPath.compress(Bitmap.CompressFormat.PNG,20,stream);
////            byte[] bytes= stream.toByteArray();
////
////            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),filePath);
////            MultipartBody.Part uploadFile = MultipartBody.Part.createFormData("image", filePath ,requestBody);
////
////            //SharedPreferences ????????? ????????????
//////            SharedPreferences preferences = getActivity().getSharedPreferences("writeSP", Context.MODE_PRIVATE);
//////            String id = preferences.getString("user_id",null);
////
////            RequestBody name = RequestBody.create(MediaType.parse("text/plain"),input_name);
////            RequestBody title = RequestBody.create(MediaType.parse("text/plain"),input_title);
////            RequestBody content = RequestBody.create(MediaType.parse("text/plain"),input_content);
////            RequestBody userid = RequestBody.create(MediaType.parse("text/plain"),"1234");
//////          RequestBody userid = RequestBody.create(MediaType.parse("text/plain"),id);
////
////            HashMap<String, RequestBody> input =new HashMap<>();
////            input.put("name",name);
////            input.put("title",title);
////            input.put("content",content);
////            input.put("userid",userid);
////
////            Log.d(TAG, "entrySet"+input.entrySet());
////            Log.d(TAG, "?????? input :"+input.get("name"));
////            Log.d(TAG, "????????? input : "+input.get("title"));
////
////            Log.d(TAG, "image " + uploadFile.body());
////            Log.d(TAG, "image " + uploadFile);
////            Log.d(TAG, "image " + uploadFile.headers());
////
////            RetrofitApiSerivce service= RetrofitClient.getInstance().create(RetrofitApiSerivce.class);
////            Log.d(TAG, "onCreateView: !!!!"+service);
////            service.postInitFeed(uploadFile,input).enqueue(new Callback<DiaryInit>() {
////                @Override
////                public void onResponse(Call<DiaryInit> call, Response<DiaryInit> response) {
////                    Log.d(TAG, " ?????? ?????? response body :" + response.body());
////                    Log.d(TAG, " ?????? ?????? ???????????? :" + response.code());
////                    if (response.isSuccessful() ) {
////                        Log.d(TAG, "?????? ?????? isSuccessful ???????????? :" + response.code());
////                        Log.d(TAG, "????????? ?????? ?????? ");
////                    }
////                }
////
////                @Override
////                public void onFailure(Call<DiaryInit> call, Throwable t) {
////                    t.printStackTrace();
////                    Log.d(TAG, "????????? ?????? ?????? ");
////                }
////            });
////
////            Log.d(TAG, filePath+ ": ?????? path ");
////
////            Log.d("onCreate: ","????????? ????????????" );
////        });
//////       --------------?????? ?????? ?????? ?????? ????????? ?????? -------------------------



//  ---------------- intent ?????? ?????? ?????? ????????? ?????? ?????? ????????? ??? -----------------

////          --- ?????? ?????? ??????  ?????? --------------------------
////            input_title=binding.title.getText().toString();
////            input_name=binding.plantsTypeSpinner.getSelectedItem().toString();
////            input_content=binding.content.getText().toString();
////
////            Log.d(TAG, "input_title_  " + input_title );
////            Log.d(TAG, "input_plant_option  " + input_name );
////            Log.d(TAG, "input_content  " + input_content );
////
////            // ????????? put??? ??? 40kb???????????? ??????????????? byte????????? ???????????? ????????????
////            ByteArrayOutputStream stream = new ByteArrayOutputStream();
////            ImgPath.compress(Bitmap.CompressFormat.PNG,20,stream);
////            byte[] bytes= stream.toByteArray();
////
////            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),filePath);
////            MultipartBody.Part uploadFile = MultipartBody.Part.createFormData("image", filePath ,requestBody);
////
////            //SharedPreferences ????????? ????????????
//////            SharedPreferences preferences = getActivity().getSharedPreferences("writeSP", Context.MODE_PRIVATE);
//////            String id = preferences.getString("user_id",null);
////
////            RequestBody name = RequestBody.create(MediaType.parse("text/plain"),input_name);
////            RequestBody title = RequestBody.create(MediaType.parse("text/plain"),input_title);
////            RequestBody content = RequestBody.create(MediaType.parse("text/plain"),input_content);
////            RequestBody userid = RequestBody.create(MediaType.parse("text/plain"),"1234");
//////          RequestBody userid = RequestBody.create(MediaType.parse("text/plain"),id);
////
////            HashMap<String, RequestBody> input =new HashMap<>();
////            input.put("name",name);
////            input.put("title",title);
////            input.put("content",content);
////            input.put("userid",userid);
////
////            Log.d(TAG, "entrySet"+input.entrySet());
////            Log.d(TAG, "?????? input :"+input.get("name"));
////            Log.d(TAG, "????????? input : "+input.get("title"));
////
////            Log.d(TAG, "image " + uploadFile.body());
////            Log.d(TAG, "image " + uploadFile);
////            Log.d(TAG, "image " + uploadFile.headers());
////
////            RetrofitApiSerivce service= RetrofitClient.getInstance().create(RetrofitApiSerivce.class);
////            Log.d(TAG, "onCreateView: !!!!"+service);
////            service.postInitFeed(uploadFile,input).enqueue(new Callback<DiaryInit>() {
////                @Override
////                public void onResponse(Call<DiaryInit> call, Response<DiaryInit> response) {
////                    Log.d(TAG, " ?????? ?????? response body :" + response.body());
////                    Log.d(TAG, " ?????? ?????? ???????????? :" + response.code());
////                    if (response.isSuccessful() ) {
////                        Log.d(TAG, "?????? ?????? isSuccessful ???????????? :" + response.code());
////                        Log.d(TAG, "????????? ?????? ?????? ");
////                    }
////                }
////
////                @Override
////                public void onFailure(Call<DiaryInit> call, Throwable t) {
////                    t.printStackTrace();
////                    Log.d(TAG, "????????? ?????? ?????? ");
////                }
////            });
////
////            Log.d(TAG, filePath+ ": ?????? path ");
////
////            Log.d("onCreate: ","????????? ????????????" );
////        });
//////       --------------?????? ?????? ?????? ?????? ????????? ?????? -------------------------



        //???????????? ????????????
        ArrayAdapter<CharSequence> adapter =ArrayAdapter.createFromResource(getActivity(),
                R.array.plants_type, R.layout.custom_spinner_layout);

        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);

        binding.plantsTypeSpinner.setAdapter(adapter);

        binding.plantsTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getActivity(),"?????? ??????????????????",Toast.LENGTH_SHORT).show();
            }
        });


        ViewGroup view =binding.getRoot();
        return view;
    }

    private int calculateInSampleSize(Uri fileUri, int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;
        try {
            InputStream inputStream = getActivity().getContentResolver().openInputStream(fileUri);

            //inJustDecodeBounds ?????? true ??? ????????? ???????????? decodeXXX() ??? ??????.
            //?????? ????????? ?????? ???????????? ?????? ????????? options ??? ?????? ??????.
            BitmapFactory.decodeStream(inputStream, null, options);
            inputStream.close();
            inputStream = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        //?????? ??????........................
        int width = options.outWidth;
        int height = options.outHeight;
        int inSampleSize = 1;

        //inSampleSize ?????? ??????
        if (height > reqHeight || width > reqWidth) {

            int halfHeight = height / 2;
            int halfWidth = width / 2;

            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    // ????????? ????????? ?????????
    private void hideKeyboard()
    {
        if (getActivity() != null && getActivity().getCurrentFocus() != null)
        {
            // ?????????????????? ????????? getActivity() ??????
            InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

}

