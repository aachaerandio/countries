package com.aachaerandio.countries.presenter;

import com.aachaerandio.countries.domain.di.CountriesModule;
import com.aachaerandio.countries.service.di.RestCountriesServiceModule;
import com.aachaerandio.countries.ui.CountryListFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {CountriesModule.class, RestCountriesServiceModule.class})
public interface CountryPresenterComponent {

    CountryListPresenter countryListPresenter();

    void inject(CountryListFragment countryListFragment);
}
