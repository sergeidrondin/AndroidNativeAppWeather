package com.sergeidrondin.weather.common;

import android.app.Application;

import com.sergeidrondin.weather.common.dependencyinjection.CompositionRoot;

public class WeatherApplication extends Application {
    private CompositionRoot mCompositionRoot;

    @Override
    public void onCreate() {
        super.onCreate();
        mCompositionRoot = new CompositionRoot();
    }

    public CompositionRoot getCompositionRoot() {
        return mCompositionRoot;
    }
}
