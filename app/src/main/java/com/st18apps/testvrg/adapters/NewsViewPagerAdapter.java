package com.st18apps.testvrg.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.st18apps.testvrg.interfaces.Constants;
import com.st18apps.testvrg.ui.fragments.TabNewsListFragment;

public class NewsViewPagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public NewsViewPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        Bundle bundle = new Bundle();

        switch (position) {
            case 0:
                Fragment emailedFragment = new TabNewsListFragment();
                bundle.putString(Constants.NEWS_TYPE, Constants.MOST_EMAILED);
                emailedFragment.setArguments(bundle);
                return emailedFragment;
            case 1:
                Fragment viewedFragment = new TabNewsListFragment();
                bundle.putString(Constants.NEWS_TYPE, Constants.MOST_VIEWED);
                viewedFragment.setArguments(bundle);
                return viewedFragment;
            case 2:
                Fragment sharedFragment = new TabNewsListFragment();
                bundle.putString(Constants.NEWS_TYPE, Constants.MOST_SHARED);
                sharedFragment.setArguments(bundle);
                return sharedFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
