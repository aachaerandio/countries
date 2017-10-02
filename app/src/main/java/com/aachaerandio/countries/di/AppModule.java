package com.aachaerandio.countries.di;

import com.aachaerandio.countries.ui.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AppModule {
    @ContributesAndroidInjector
    abstract MainActivity provideMainActivity();
}
