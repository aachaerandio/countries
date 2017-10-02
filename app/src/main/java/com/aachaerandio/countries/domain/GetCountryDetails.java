package com.aachaerandio.countries.domain;

import com.aachaerandio.countries.model.Country;
import com.aachaerandio.countries.service.RestCountriesApiService;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetCountryDetails {

    RestCountriesApiService marvelApiService;

    @Inject
    public GetCountryDetails(RestCountriesApiService marvelApiService) {
        this.marvelApiService = marvelApiService;
    }

    public Observable<Country> execute(String countryId){
        return marvelApiService.getService()
                .getCountryByCode(countryId);
    }
}
