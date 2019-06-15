package com.st18apps.testvrg.ui.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.st18apps.testvrg.R;
import com.st18apps.testvrg.adapters.NewsRecyclerAdapter;
import com.st18apps.testvrg.interfaces.Constants;
import com.st18apps.testvrg.ui.BaseFragment;
import com.st18apps.testvrg.viewmodels.NewsViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TabNewsListFragment extends BaseFragment implements NewsRecyclerAdapter.ItemClickListener {

    @BindView(R.id.recycler_news)
    RecyclerView recyclerNews;

    private NewsViewModel newsViewModel;
    private NewsRecyclerAdapter newsRecyclerAdapter;
    private String newsType;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_news_list, container, false);

        ButterKnife.bind(this, view);

        newsViewModel = ViewModelProviders.of(getActivity()).get(NewsViewModel.class);

        if (getArguments() != null) {
            newsType = getArguments().getString(Constants.NEWS_TYPE, Constants.MOST_EMAILED);
        }

        setRecycler();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setDataToUI();

    }

    private void setRecycler() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerNews.setLayoutManager(linearLayoutManager);

        newsRecyclerAdapter = new NewsRecyclerAdapter(this);
        recyclerNews.setAdapter(newsRecyclerAdapter);
    }

    private void setDataToUI() {
        switch (newsType) {
            case Constants.MOST_EMAILED:
                newsViewModel.getMostEmailed().observe(this, results ->
                        newsRecyclerAdapter.setData(results));
                break;

            case Constants.MOST_VIEWED:
                newsViewModel.getMostViewed().observe(this, results ->
                        newsRecyclerAdapter.setData(results));
                break;
            case Constants.MOST_SHARED:
                newsViewModel.getMostShared().observe(this, results ->
                        newsRecyclerAdapter.setData(results));
                break;
        }
    }

    @Override
    public void onItemClick(int position) {

    }
}
