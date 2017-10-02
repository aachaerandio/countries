package com.aachaerandio.countries.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aachaerandio.countries.R;
import com.aachaerandio.countries.model.Country;
import com.aachaerandio.countries.presenter.CountryListPresenter;

import java.util.List;

public class CountryListFragment extends Fragment implements CountryListPresenter.UserInterface {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_countries, container, false);;

        return rootView;
    }

    @Override
    public void showCountries(List<Country> countries) {

    }

    @Override
    public void onCountryClicked(String countryId) {

    }

    @Override
    public void showErrorMessage(String error) {

    }

    @Override
    public void showLoading(boolean isLoading) {

    }
}
