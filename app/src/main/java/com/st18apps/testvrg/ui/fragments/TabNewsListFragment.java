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
import com.st18apps.testvrg.model.NewsData;
import com.st18apps.testvrg.ui.BaseFragment;
import com.st18apps.testvrg.utils.FragmentUtil;
import com.st18apps.testvrg.viewmodels.NewsViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TabNewsListFragment extends BaseFragment implements NewsRecyclerAdapter.ItemClickListener {

    @BindView(R.id.recycler_news)
    RecyclerView recyclerNews;

    private NewsViewModel newsViewModel;
    private NewsRecyclerAdapter newsRecyclerAdapter;
    private String newsType;
    private List<NewsData> favoritesNews;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflateWithLoadingIndicator(R.layout.fragment_tab_news_list, container);

        ButterKnife.bind(this, view);

        newsViewModel = ViewModelProviders.of(getActivity()).get(NewsViewModel.class);

        if (getArguments() != null) {
            newsType = getArguments().getString(Constants.NEWS_TYPE, Constants.MOST_EMAILED);
        }

        favoritesNews = new ArrayList<>();

        loadNews();

        setRecycler();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setDataToUI();

        newsViewModel.getFavoritesNews().observe(this, newsData ->
                favoritesNews = newsData);

        // show toast if errors
//        newsViewModel.getStatus().observe(this, message ->
//                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show());

    }

    private void setRecycler() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerNews.setLayoutManager(linearLayoutManager);

        newsRecyclerAdapter = new NewsRecyclerAdapter(this);
        recyclerNews.setAdapter(newsRecyclerAdapter);
    }

    private void loadNews() {

        setLoading(true);

        switch (newsType) {
            case Constants.MOST_EMAILED:
                newsViewModel.loadMostEmailed();
                break;

            case Constants.MOST_VIEWED:
                newsViewModel.loadMostViewed();
                break;

            case Constants.MOST_SHARED:
                newsViewModel.loadMostShared();
                break;
        }
    }

    private void setDataToUI() {
        switch (newsType) {
            case Constants.MOST_EMAILED:
                newsViewModel.getMostEmailed().observe(this, newsData -> {
                    setLoading(false);
                    newsRecyclerAdapter.setData(checkFavorites(newsData));
                });
                break;

            case Constants.MOST_VIEWED:
                newsViewModel.getMostViewed().observe(this, newsData -> {
                    setLoading(false);
                    newsRecyclerAdapter.setData(checkFavorites(newsData));
                });
                break;

            case Constants.MOST_SHARED:
                newsViewModel.getMostShared().observe(this, newsData -> {
                    setLoading(false);
                    newsRecyclerAdapter.setData(checkFavorites(newsData));
                });
                break;
        }
    }

    private List<NewsData> checkFavorites(List<NewsData> newsDataList) {

        for (int i = 0; i < newsDataList.size(); i++) {
            for (int j = 0; j < favoritesNews.size(); j++) {
                if (newsDataList.get(i).getId() == favoritesNews.get(j).getId()) {
                    newsDataList.get(i).setLiked(true);
                }
            }
        }

        return newsDataList;
    }

    @Override
    public void onItemClick(int position) {
        newsViewModel.select(newsRecyclerAdapter.getData().get(position));
        FragmentUtil.replaceFragment(getActivity().getSupportFragmentManager(),
                new DetailNewsFragment(), true);
    }

    @Override
    public void onLikeClick(int position) {

        if (newsRecyclerAdapter.getData().get(position).isLiked()) {
            newsViewModel.delete(newsRecyclerAdapter.getData().get(position));
        } else {
            newsViewModel.insert(newsRecyclerAdapter.getData().get(position));
        }
        newsRecyclerAdapter.updateLike(position);

    }
}
