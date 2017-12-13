package com.example.owner.giveback.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.owner.giveback.NewUserActivity;
import com.example.owner.giveback.newUserFragments.FirstFragment;
import com.example.owner.giveback.newUserFragments.SecondFragment;
import com.example.owner.giveback.newUserFragments.ThirdFragment;

/**
 * Created by owner on 12/10/17.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter{

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0: return FirstFragment.newInstance("FirstFragment");
            case 1: return SecondFragment.newInstance("SecondFragment");
            case 2: return ThirdFragment.newInstance("ThirdFragment");
            default: return ThirdFragment.newInstance("ThirdFragment Default");
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
