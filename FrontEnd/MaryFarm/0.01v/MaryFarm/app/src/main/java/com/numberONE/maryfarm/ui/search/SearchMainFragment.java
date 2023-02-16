package com.numberONE.maryfarm.ui.search;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.numberONE.maryfarm.MainActivity;
import com.numberONE.maryfarm.R;
import com.numberONE.maryfarm.databinding.FragmentSearchMainBinding;
import com.numberONE.maryfarm.ui.home.HomeFragment;

public class SearchMainFragment extends Fragment implements MainActivity.OnBackPressedListener{
    private static final String TAG="SearchMainFragment";
    FragmentSearchMainBinding binding;

    RecyclerView recyclerView_search;
    RecyclerView.LayoutManager layoutManager_search;
    RecyclerView.Adapter adapter_search;

    public SearchMainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding =FragmentSearchMainBinding.inflate(inflater,container,false);

        recyclerView_search=binding.searchRecycler;

//        recyclerView_search.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        layoutManager_search=new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        recyclerView_search.setLayoutManager(layoutManager_search);

        String[] title={"호박 키우기 1일차 ","호박과 ~ing","식물 키우기 초보","식집사 7일차"};
        String[] nickname={"김유섭","백진관","장이석","강승희"};
        int[] image={R.drawable.pumpkin2,R.drawable.pumpkin1,R.drawable.pumpkin5,R.drawable.pumpkin10};
        int[] image1={R.drawable.pumpkin3,R.drawable.pumpkin8,R.drawable.pumpkin9,R.drawable.pumpkin7};
        int[] image2={R.drawable.pumpkin4,R.drawable.pumpkin6,R.drawable.pumpkin11,R.drawable.leaf_color_green};
        int[] profile={R.drawable.helloflower,R.drawable.profilebaek,R.drawable.profilekim,R.drawable.profilekang};

        adapter_search=new SearchAdapter(image,image1,image2,profile,nickname,title);

        recyclerView_search.setAdapter(adapter_search);

        ViewGroup view =binding.getRoot();
        return view;
    }
    //    ---- 뒤로가기 버튼 로직 (몸통은 메인액티비티에 ) -------
    @Override
    public void onBack() {
        HomeFragment fragment =new HomeFragment();
        Log.d(TAG, " 뒤로가기 버튼 실행  ");
        //리스너를 설정하기 위해 Activity 받아오기
        MainActivity activity = (MainActivity)getActivity();
        // 한번 뒤로가기 버튼을 눌렀다면 Listener를 null 로 해제
        activity.setOnBackPressedListener(null);
        // 보드 메인으로 이동
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_activity,fragment).commit();
    }

    //   Fragment 호출 시 반드시 호출되는 오바라이드 메소드
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach: ");
        ((MainActivity)context).setOnBackPressedListener(this);
    }
}