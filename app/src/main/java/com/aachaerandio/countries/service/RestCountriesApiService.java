package com.aachaerandio.countries.service;

import com.aachaerandio.countries.model.Country;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class RestCountriesApiService {

    private RestCountriesService restCountriesService;

    public RestCountriesApiService(String serviceUrl) {
        restCountriesService = new Retrofit.Builder()
                .baseUrl(serviceUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RestCountriesService.class);
    }

    public RestCountriesService getService() {
        return this.restCountriesService;
    }

    public interface RestCountriesService {

        @GET("all")
        Observable<List<Country>> getCountries();

        @GET("alpha/{code}")
        Observable<Country> getCountryByCode(@Path("code") String code);
    }
}
