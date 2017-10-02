package com.aachaerandio.countries.domain.di;

import com.aachaerandio.countries.domain.GetCountries;
import com.aachaerandio.countries.domain.GetCountryDetails;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

@Module
public class TestCountryModule {

    @Provides
    @Singleton
    GetCountries provideGetCountries() {
        return mock(GetCountries.class);
    }

    @Provides
    @Singleton
    GetCountryDetails provideGetCountyDetails() {
        return mock(GetCountryDetails.class);
    }
}
