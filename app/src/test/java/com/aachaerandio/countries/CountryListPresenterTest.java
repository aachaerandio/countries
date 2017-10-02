package com.aachaerandio.countries;

import com.aachaerandio.countries.domain.GetCountries;
import com.aachaerandio.countries.domain.di.DaggerTestCountryComponent;
import com.aachaerandio.countries.model.Country;
import com.aachaerandio.countries.presenter.CountryListPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CountryListPresenterTest extends RxJavaSchedulerTest {

    @Inject
    GetCountries getCountries;
    @Inject
    CountryListPresenter countryListPresenter;
    @Mock
    private CountryListPresenter.UserInterface mockView;

    @Before
    public void setup(){
        DaggerTestCountryComponent.builder().build().inject(this);
    }

    @Test
    public void givenCountriesLoaded_ThenShowCountries() throws Exception {
        countryListPresenter.setView(mockView);

        when(getCountries.execute()).thenReturn(Observable.just(createCountryList()));

        countryListPresenter.loadCountries();

        ArgumentCaptor<List<Country>> argumentCaptor = ArgumentCaptor.forClass(ArrayList.class);

        verify(mockView, times(1)).showLoading(eq(true));
        verify(mockView, times(1)).showLoading(eq(false));
        verify(mockView, times(1)).showCountries(argumentCaptor.capture());

        assertEquals(1, argumentCaptor.getValue().size());
        assertEquals("1", argumentCaptor.getValue().get(0).getAlpha2Code());
    }

    @Test
    public void givenLoadCountriesFailed_ThenShowError() throws Exception {
        countryListPresenter.setView(mockView);

        Observable<List<Country>> testError = Observable.error(new RuntimeException("TestError"));

        when(getCountries.execute()).thenReturn(testError);

        countryListPresenter.loadCountries();

        verify(mockView, times(1)).showLoading(eq(true));
        verify(mockView, times(1)).showErrorMessage("TestError");
    }

    private List<Country> createCountryList() {
        Country country = new Country();
        country.setAlpha2Code("1");
        country.setName("Spain");

        return Arrays.asList(country);
    }
}
