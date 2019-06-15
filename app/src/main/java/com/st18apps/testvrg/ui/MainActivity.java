package com.st18apps.testvrg.ui;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;

import com.st18apps.testvrg.R;
import com.st18apps.testvrg.ui.fragments.FavoritesFragment;
import com.st18apps.testvrg.ui.fragments.NewsFragment;
import com.st18apps.testvrg.utils.FragmentUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.nav_view)
    BottomNavigationView navView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_news:
                FragmentUtil.replaceFragment(getSupportFragmentManager(),
                        new NewsFragment(), false);
                return true;
            case R.id.navigation_favorites:
                FragmentUtil.replaceFragment(getSupportFragmentManager(),
                        new FavoritesFragment(), false);
                return true;
        }
        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navView.setSelectedItemId(R.id.navigation_news);
    }

}
