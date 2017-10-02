package com.aachaerandio.countries.domain;

import com.aachaerandio.countries.model.Country;
import com.aachaerandio.countries.service.RestCountriesApiService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetCountries {

    RestCountriesApiService restCountriesApiService;

    @Inject
    public GetCountries(RestCountriesApiService restCountriesApiService) {
        this.restCountriesApiService = restCountriesApiService;
    }

    public Observable<List<Country>> execute(){
        return restCountriesApiService.getService().getCountries();
    }
}
