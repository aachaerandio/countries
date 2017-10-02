package com.aachaerandio.countries.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aachaerandio.countries.Constants;
import com.aachaerandio.countries.R;
import com.aachaerandio.countries.model.Country;
import com.aachaerandio.countries.presenter.CountryDetailsPresenter;
import com.aachaerandio.countries.presenter.DaggerCountryPresenterComponent;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CountryDetailsFragment extends Fragment implements CountryDetailsPresenter.UserInterface {

    Unbinder unbinder;
    @BindView(R.id.image_flag)
    ImageView image;
    @BindView(R.id.country_capital)
    TextView capital;
    @BindView(R.id.country_region)
    TextView region;
    @BindView(R.id.country_subregion)
    TextView subregion;
    @BindView(R.id.country_population)
    TextView population;
    @BindView(R.id.country_area)
    TextView area;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    CountryDetailsPresenter countryDetailsPresenter;
    private String countryId;

    public CountryDetailsFragment()
    {
    }

    public static CountryDetailsFragment getInstance(String countryId) {
        Bundle args = new Bundle();
        args.putString(Constants.COUNTRY_ID, countryId);
        CountryDetailsFragment countryDetailsFragment = new CountryDetailsFragment();
        countryDetailsFragment.setArguments(args);

        return countryDetailsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerCountryPresenterComponent.builder().build().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_country_details, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(savedInstanceState == null) {
            countryId = getArguments().getString(Constants.COUNTRY_ID);
        } else {
            countryId = savedInstanceState.getString(Constants.COUNTRY_ID);
        }
        countryDetailsPresenter.setView(this);
        countryDetailsPresenter.loadDetails(countryId);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(Constants.COUNTRY_ID, countryId);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        unbinder.unbind();
        countryDetailsPresenter.dispose();
    }

    @Override
    public void showDetails(Country country) {
        updateToolbarTitle(country.getName());
        capital.setText(country.getCapital());
        region.setText(country.getRegion());
        subregion.setText(country.getRegion());
        population.setText(String.valueOf(country.getPopulation()));
        area.setText(String.valueOf(country.getArea()));
        Picasso.with(getContext())
                .load(country.buildUrl())
                .placeholder(R.color.colorAccent)
                .into(image);
    }

    private void updateToolbarTitle(String title) {
        collapsingToolbar.setTitleEnabled(false);
        toolbar.setTitle(title);
    }

    @Override
    public void showError(String error) {
        String userError = getString(R.string.error_general) + error;
        Snackbar.make(coordinatorLayout, userError, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.error_action_retry, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        countryDetailsPresenter.loadDetails(countryId);
                    }
                }).show();
    }
}
