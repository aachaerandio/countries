package com.aachaerandio.countries.domain.di;

import com.aachaerandio.countries.CountryDetailsPresenterTest;
import com.aachaerandio.countries.CountryListPresenterTest;
import com.aachaerandio.countries.presenter.CountryDetailsPresenter;
import com.aachaerandio.countries.presenter.CountryListPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {TestCountryModule.class})
public interface TestCountryComponent {
    CountryListPresenter countryListPresenter();
    CountryDetailsPresenter countryDetailsPresenter();

    void inject(CountryListPresenterTest presenter);
    void inject(CountryDetailsPresenterTest presenter);
}
