package com.goalwise.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.goalwise.fragments.FragmentRowFirst;

/**
 * Created by Techno Blogger on 20/2/17.
 */

public class AdapterFirstRow extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public AdapterFirstRow(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                FragmentRowFirst tab1 = new FragmentRowFirst();
                return tab1;
            case 1:
                FragmentRowFirst tab2 = new FragmentRowFirst();
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