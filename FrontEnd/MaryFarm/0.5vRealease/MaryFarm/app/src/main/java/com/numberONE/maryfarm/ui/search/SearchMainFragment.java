package com.numberONE.maryfarm.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.numberONE.maryfarm.R;
import com.numberONE.maryfarm.databinding.FragmentSearchMainBinding;

public class SearchMainFragment extends Fragment {

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

        recyclerView_search.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        layoutManager_search=new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        recyclerView_search.setLayoutManager(layoutManager_search);

        String[] title=new String[11];
        String[] nickname=new String[11];
//        String[] date=new String[11];

        int[] image={R.drawable.gallery_icon,R.drawable.gallery_icon,R.drawable.gallery_icon,R.drawable.gallery_icon,R.drawable.gallery_icon,
                R.drawable.gallery_icon,R.drawable.gallery_icon,R.drawable.gallery_icon,R.drawable.gallery_icon,R.drawable.gallery_icon};
        int[] image1={R.drawable.gallery_icon,R.drawable.gallery_icon,R.drawable.gallery_icon,R.drawable.gallery_icon,R.drawable.gallery_icon,
                R.drawable.gallery_icon,R.drawable.gallery_icon,R.drawable.gallery_icon,R.drawable.gallery_icon,R.drawable.gallery_icon};
        int[] image2={R.drawable.gallery_icon,R.drawable.gallery_icon,R.drawable.gallery_icon,R.drawable.gallery_icon,R.drawable.gallery_icon,
                R.drawable.gallery_icon,R.drawable.gallery_icon,R.drawable.gallery_icon,R.drawable.gallery_icon,R.drawable.gallery_icon};
        int[] profile={R.drawable.loading_icon,R.drawable.loading_icon,R.drawable.loading_icon,R.drawable.loading_icon,R.drawable.loading_icon,
                R.drawable.loading_icon,R.drawable.loading_icon,R.drawable.loading_icon,R.drawable.loading_icon,R.drawable.loading_icon};

        for(int i=1;i<=10;i++){
            title[i]="타이틀"+i; nickname[i]="닉네임"+i ;
        }

        adapter_search=new SearchAdapter(image,image1,image2,profile,nickname,title);

        recyclerView_search.setAdapter(adapter_search);

        ViewGroup view =binding.getRoot();
        return view;
    }
}