package com.aachaerandio.countries.ui;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aachaerandio.countries.BuildConfig;
import com.aachaerandio.countries.R;
import com.aachaerandio.countries.model.Country;
import com.aachaerandio.countries.presenter.CountryListPresenter;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.ViewHolder>{

    private List<Country> countries;
    private Context context;
    private final CountryListPresenter.UserInterface view;

    public CountriesAdapter(List<Country> countries, CountryListPresenter.UserInterface view) {
        this.countries = countries;
        this.view = view;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.grid_item_country, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.country = countries.get(position);
        holder.title.setText(holder.country.getName());
        Picasso.with(context)
                .load(holder.country.buildUrl())
                .placeholder(R.color.colorAccent)
                .into(holder.flag);
        holder.itemView.setOnClickListener(holder);
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.country_flag)
        ImageView flag;
        @BindView(R.id.country_title)
        TextView title;

        public Country country;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View view) {
            CountriesAdapter.this.view.onCountryClicked(country.alpha3Code);
        }
    }
}
