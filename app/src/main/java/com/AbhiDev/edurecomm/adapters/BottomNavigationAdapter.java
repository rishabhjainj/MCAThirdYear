package com.AbhiDev.edurecomm.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.wireout.Fragments.ExploreFragment;
import com.wireout.Fragments.HomeFragment;


import java.util.ArrayList;

/**
 * Created by Rishabh on 2/14/2018.
 */

public class BottomNavigationAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments = new ArrayList<>();

    private android.support.v4.app.Fragment currentFragment;

    Context context;
    public BottomNavigationAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context= context;
        fragments.clear();
        fragments.add(HomeFragment.newInstance(0));
        fragments.add(ExploreFragment.newInstance(1));
        //fragments.add(ProfileBottomFragment.newInstance(2));

    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }


    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {

            if (getCurrentFragment() != object) {
                currentFragment = ((android.support.v4.app.Fragment) object);

            }
            super.setPrimaryItem(container, position, object);

    }


    /**
     * Get the current fragment
     */
    public android.support.v4.app.Fragment getCurrentFragment() {
        return currentFragment;
    }
}