package com.st18apps.testvrg.ui.fragments;

import android.arch.lifecycle.Observer;
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
import com.st18apps.testvrg.model.NewsData;
import com.st18apps.testvrg.ui.BaseFragment;
import com.st18apps.testvrg.utils.FragmentUtil;
import com.st18apps.testvrg.viewmodels.NewsViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoritesFragment extends BaseFragment implements NewsRecyclerAdapter.ItemClickListener {

    @BindView(R.id.recycler_favorites)
    RecyclerView recyclerFavorites;
    private NewsRecyclerAdapter newsRecyclerAdapter;
    private NewsViewModel newsViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        ButterKnife.bind(this, view);

        newsViewModel = ViewModelProviders.of(getActivity()).get(NewsViewModel.class);

        setRecycler();

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

        newsViewModel.getFavoritesNews().observe(this, newsData ->
                newsRecyclerAdapter.setData(newsData));

    }

    private void setRecycler() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerFavorites.setLayoutManager(linearLayoutManager);

        newsRecyclerAdapter = new NewsRecyclerAdapter(this);
        recyclerFavorites.setAdapter(newsRecyclerAdapter);
    }

    @Override
    public void onItemClick(int position) {
        newsViewModel.select(newsRecyclerAdapter.getData().get(position));
        FragmentUtil.replaceFragment(getActivity().getSupportFragmentManager(),
                new DetailNewsFragment(), true);
    }

    @Override
    public void onLikeClick(int position) {
        newsViewModel.delete(newsRecyclerAdapter.getData().get(position));
        newsRecyclerAdapter.deleteItem(position);
    }
}
