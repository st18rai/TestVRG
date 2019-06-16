package com.st18apps.testvrg.ui.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.st18apps.testvrg.R;
import com.st18apps.testvrg.ui.BaseFragment;
import com.st18apps.testvrg.ui.MainActivity;
import com.st18apps.testvrg.viewmodels.NewsViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailNewsFragment extends BaseFragment {

    @BindView(R.id.webView)
    WebView webView;

    private NewsViewModel newsViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_news, container, false);

        ButterKnife.bind(this, view);

        newsViewModel = ViewModelProviders.of(getActivity()).get(NewsViewModel.class);


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        ((MainActivity) getActivity()).setBackButtonEnabled(true);

    }

    @Override
    public void onStop() {
        super.onStop();

        ((MainActivity) getActivity()).setBackButtonEnabled(false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        newsViewModel.getSelected().observe(this, result ->
                loadNewsDetail(result.getUrl()));

    }

    private void loadNewsDetail(String url) {

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);

    }

}
