package com.st18apps.testvrg.ui.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.st18apps.testvrg.R;
import com.st18apps.testvrg.adapters.NewsViewPagerAdapter;
import com.st18apps.testvrg.ui.BaseFragment;
import com.st18apps.testvrg.viewmodels.NewsViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsFragment extends BaseFragment {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.news_pager)
    ViewPager newsPager;

    private NewsViewModel newsViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        ButterKnife.bind(this, view);

        newsViewModel = ViewModelProviders.of(getActivity()).get(NewsViewModel.class);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        // set title and toolbar state
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init();

    }

    private void init() {
        NewsViewPagerAdapter placePagerAdapter = new NewsViewPagerAdapter(
                (getChildFragmentManager()), tabLayout.getTabCount());
        newsPager.setAdapter(placePagerAdapter);
        newsPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                newsPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}
