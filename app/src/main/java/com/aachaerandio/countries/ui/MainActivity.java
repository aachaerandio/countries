package com.aachaerandio.countries.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.aachaerandio.countries.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolBar();

        CountryListFragment comicListFragment = new CountryListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, comicListFragment).commit();
    }

    private void setToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setTitle(R.string.countries_app);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }
    }
}