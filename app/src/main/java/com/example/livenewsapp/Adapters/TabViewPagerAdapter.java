package com.example.livenewsapp.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.livenewsapp.Fragments.Eveything.EverythingFragment;
import com.example.livenewsapp.Fragments.HeadLines.HeadLinesFragment;
import com.example.livenewsapp.Fragments.Sources.SourcesFragment;

public class TabViewPagerAdapter extends FragmentPagerAdapter {

    public TabViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        if (position == 0){
            fragment = new HeadLinesFragment();
        }
        if (position == 1){
           fragment = new EverythingFragment();
        }
        if (position == 2){
            fragment = new SourcesFragment();
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        if (position == 0){
            title = "HeadLines";
        }
        if (position == 1){
            title = "Everything";
        }
        if (position == 2){
            title = "Sources";
        }
        return title;
    }
}
