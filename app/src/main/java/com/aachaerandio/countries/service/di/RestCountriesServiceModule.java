package com.aachaerandio.countries.service.di;

import com.aachaerandio.countries.BuildConfig;
import com.aachaerandio.countries.service.RestCountriesApiService;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RestCountriesServiceModule {

    @Provides
    @Singleton
    RestCountriesApiService provideRestCountriesApiService(@Named("url") String serviceUrl){
        return new RestCountriesApiService(serviceUrl);
    }

    @Provides
    @Named("url")
    String provideServiceUrl(){
        return BuildConfig.RESTCOUNTRIES_BASE_URL;
    }
}
