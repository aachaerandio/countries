package com.aachaerandio.countries.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.aachaerandio.countries.R;
import com.aachaerandio.countries.model.Country;
import com.aachaerandio.countries.presenter.CountryListPresenter;
import com.aachaerandio.countries.presenter.DaggerCountryPresenterComponent;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CountryListFragment extends Fragment implements CountryListPresenter.UserInterface {

    @BindView(R.id.countries_recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.loading)
    ProgressBar loading;

    @Inject
    CountryListPresenter countyListPresenter;

    Unbinder unbinder;
    private CountriesAdapter adapter;
    private List<Country> countries = new ArrayList<>();
    private Callback callback;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerCountryPresenterComponent.builder().build().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_countries, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CountriesAdapter(countries, this);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        countyListPresenter.setView(this);
        if(savedInstanceState == null) {
            countyListPresenter.loadCountries();
        }
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        unbinder.unbind();
        countyListPresenter.dispose();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callback = (Callback) context;
    }

    @Override
    public void onDetach() {
        callback = null;
        super.onDetach();
    }

    @Override
    public void showCountries(List<Country> countries) {
        this.countries.clear();
        this.countries.addAll(countries);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCountryClicked(String countryId) {
        callback.onCountryClicked(countryId);
    }

    @Override
    public void showErrorMessage(String error) {
        String userError = getString(R.string.error_general) + error;
        Snackbar.make(coordinatorLayout, userError, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.error_action_retry, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        countyListPresenter.loadCountries();
                    }
                }).show();
    }

    @Override
    public void showLoading(boolean isLoading) {
        loading.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }

    public interface Callback
    {
        void onCountryClicked(String countryId);
    }
}
