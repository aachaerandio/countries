package com.aachaerandio.countries.domain.di;

import com.aachaerandio.countries.domain.GetCountries;
import com.aachaerandio.countries.service.RestCountriesApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class CountriesModule {

    @Provides
    @Singleton
    GetCountries provideGetComics(RestCountriesApiService marvelApiService) {
        return new GetCountries(marvelApiService);
    }

}
