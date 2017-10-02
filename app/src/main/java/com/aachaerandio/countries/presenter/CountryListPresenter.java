package com.aachaerandio.countries.presenter;


import com.aachaerandio.countries.model.Country;

import java.util.List;

public class CountryListPresenter {

    private UserInterface view;


    public interface UserInterface {
        void showCountries(List<Country> countries);
        void onCountryClicked(String countryId);
        void showErrorMessage(String error);
        void showLoading(boolean isLoading);
    }
}
