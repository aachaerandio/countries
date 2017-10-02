package com.aachaerandio.countries;

import com.aachaerandio.countries.domain.GetCountryDetails;
import com.aachaerandio.countries.domain.di.DaggerTestCountryComponent;
import com.aachaerandio.countries.model.Country;
import com.aachaerandio.countries.presenter.CountryDetailsPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.inject.Inject;

import io.reactivex.Observable;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CountryDetailsPresenterTest extends RxJavaSchedulerTest {

    @Inject
    GetCountryDetails getCountryDetails;
    @Inject
    CountryDetailsPresenter countryDetailsPresenter;
    @Mock
    private CountryDetailsPresenter.UserInterface mockView;

    @Before
    public void setup(){
        DaggerTestCountryComponent.builder().build().inject(this);
    }

    @Test
    public void givenCountryDetailsLoaded_ThenShowDetails() throws Exception {
        countryDetailsPresenter.setView(mockView);

        when(getCountryDetails.execute("1")).thenReturn(Observable.just(createCountry()));

        countryDetailsPresenter.loadDetails("1");

        ArgumentCaptor<Country> argumentCaptor = ArgumentCaptor.forClass(Country.class);

        verify(mockView, times(1)).showDetails(argumentCaptor.capture());

        assertEquals("1", argumentCaptor.getValue().getAlpha2Code());
    }

    @Test
    public void givenLoadDetailsFailed_ThenShowError() throws Exception {
        countryDetailsPresenter.setView(mockView);

        Observable<Country> testError = Observable.error(new RuntimeException("TestError"));

        when(getCountryDetails.execute("1")).thenReturn(testError);

        countryDetailsPresenter.loadDetails("1");

        verify(mockView, times(1)).showError("TestError");
    }

    private Country createCountry() {
        Country country = new Country();
        country.setAlpha2Code("1");
        country.setName("Spain");
        return country;
    }
}
