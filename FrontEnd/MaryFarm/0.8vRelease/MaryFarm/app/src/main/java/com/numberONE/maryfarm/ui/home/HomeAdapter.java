package com.numberONE.maryfarm.ui.home;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.numberONE.maryfarm.ui.home.Calendar.CalendarFragment;
import com.numberONE.maryfarm.ui.home.FarmFeed.FeedFragment;


public class HomeAdapter extends FragmentStateAdapter {

    public int cnt;

    public HomeAdapter(FragmentActivity fa, int cnt){
        super(fa);
        this.cnt=cnt;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        int idx= getRealPosition(position);

        if(idx==0) return new FeedFragment();
        else return new CalendarFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public int getRealPosition(int position){
        return position;
    }
}
