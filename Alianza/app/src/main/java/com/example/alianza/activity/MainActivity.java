package com.example.alianza.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.alianza.adapters.MainAdapter;
import com.example.alianza.fragments.MatchFragment;
import com.example.alianza.fragments.NewsFragment;
import com.example.alianza.R;
import com.example.alianza.fragments.TeamFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Adding Toolbar to Main screen
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Setting ViewPager for each Tabs
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        // Set Tabs inside Toolbar
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);


    }


    // Add Fragments to Tabs
    private void setupViewPager(ViewPager viewPager) {
        MainAdapter adapter = new MainAdapter(getSupportFragmentManager());
        adapter.addFragment(new NewsFragment(), getResources().getString(R.string.tabs_name_news));
        adapter.addFragment(new TeamFragment(), getResources().getString(R.string.tabs_name_team));
        adapter.addFragment(new MatchFragment(), getResources().getString(R.string.tabs_name_match));
        viewPager.setAdapter(adapter);
    }

}
