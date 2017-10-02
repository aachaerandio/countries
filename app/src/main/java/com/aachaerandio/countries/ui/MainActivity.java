package com.aachaerandio.countries.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.aachaerandio.countries.Constants;
import com.aachaerandio.countries.R;

public class MainActivity extends AppCompatActivity implements CountryListFragment.Callback {

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

    @Override
    public void onCountryClicked(String countryId) {
        startCountryDetailsActivity(countryId);
    }

    private void startCountryDetailsActivity(String countryId) {
        Intent intent = new Intent(this, CountryDetailsActivity.class);
        Bundle extras = new Bundle();
        extras.putString(Constants.COUNTRY_ID, countryId);
        intent.putExtras(extras);
        startActivity(intent);
    }
}
