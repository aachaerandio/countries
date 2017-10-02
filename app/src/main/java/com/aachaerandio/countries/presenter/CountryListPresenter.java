package com.aachaerandio.countries.presenter;

import com.aachaerandio.countries.domain.GetCountries;
import com.aachaerandio.countries.model.Country;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CountryListPresenter {

    private UserInterface view;
    GetCountries getCountries;
    private CompositeDisposable disposables = new CompositeDisposable();

    @Inject
    public CountryListPresenter(GetCountries getCountries) {
        this.getCountries = getCountries;
    }

    public void setView(CountryListPresenter.UserInterface view)
    {
        this.view = view;
    }

    public void loadCountries() {

        Disposable disposable = getCountries.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Country>>() {
                    @Override
                    public void accept(List<Country> countries) throws Exception {
                        view.showLoading(false);
                        if (countries != null) {
                            view.showCountries(countries);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable t) throws Exception {
                        view.showLoading(false);
                        view.showErrorMessage(t.getMessage());
                    }
                });

        disposables.add(disposable);
    }

    public void dispose() {
        disposables.dispose();
    }

    public interface UserInterface {
        void showCountries(List<Country> countries);
        void onCountryClicked(String countryId);
        void showErrorMessage(String error);
        void showLoading(boolean isLoading);
    }
}
