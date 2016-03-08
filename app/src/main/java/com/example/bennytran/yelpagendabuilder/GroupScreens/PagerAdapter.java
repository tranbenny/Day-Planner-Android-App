package com.example.bennytran.yelpagendabuilder.GroupScreens;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by vivianso on 3/7/16.
 */
public class PagerAdapter extends FragmentPagerAdapter {

    private static final int NUM_PAGES = 3;

    public PagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return GroupPlanFragment.newInstance(0, "Page #1");
            case 1:
                return GroupChatFragment.newInstance(1, "Page #2");
            case 2:
                return GroupPreferencesFragment.newInstance(2, "Page #3");
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }
}
