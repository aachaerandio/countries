package com.aachaerandio.countries.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.aachaerandio.countries.Constants;
import com.aachaerandio.countries.R;

public class CountryDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details);

        setToolBar();

        if (savedInstanceState == null)
        {
            Bundle extras = getIntent().getExtras();
            if (extras != null && extras.containsKey(Constants.COUNTRY_ID))
            {
                String countryId = extras.getString(Constants.COUNTRY_ID);
                CountryDetailsFragment countryDetailsFragment = CountryDetailsFragment.getInstance(countryId);
                getSupportFragmentManager().beginTransaction().replace(R.id.country_details_container, countryDetailsFragment).commit();
            }
        }
    }

    private void setToolBar() {
        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setTitle(R.string.country_details);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }
    }
}
