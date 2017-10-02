package com.aachaerandio.countries.presenter;

import com.aachaerandio.countries.domain.GetCountryDetails;
import com.aachaerandio.countries.model.Country;
import com.aachaerandio.countries.ui.CountryDetailsFragment;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CountryDetailsPresenter {

    private CountryDetailsPresenter.UserInterface view;

    GetCountryDetails getCountryDetails;
    private CompositeDisposable disposables = new CompositeDisposable();

    @Inject
    public CountryDetailsPresenter(GetCountryDetails getCountryDetails) {
        this.getCountryDetails = getCountryDetails;
    }

    public void setView(CountryDetailsFragment view) {
        this.view = view;
    }

    public void loadDetails(String countryId) {
        Disposable disposable = getCountryDetails.execute(countryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Country>() {
                    @Override
                    public void accept(Country country) throws Exception {
                        if (country != null) {
                            view.showDetails(country);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable t) throws Exception {
                        view.showError(t.getMessage());
                    }
                });
        disposables.add(disposable);
    }

    public void dispose() {
        disposables.dispose();
    }

    public interface UserInterface {
        void showDetails(Country country);
        void showError(String error);
    }
}
