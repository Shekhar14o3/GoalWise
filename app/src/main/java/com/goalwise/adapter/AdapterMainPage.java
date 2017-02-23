package com.goalwise.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.goalwise.fragments.FragmentOverView;
import com.goalwise.fragments.FragmentFunds;

/**
 * Created by Techno Blogger on 19/2/17.
 */

public class AdapterMainPage extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public AdapterMainPage(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                FragmentOverView tab1 = new FragmentOverView();
                return tab1;
            case 1:
                FragmentFunds tab2 = new FragmentFunds();
                return tab2;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}