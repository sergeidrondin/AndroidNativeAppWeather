package com.sergeidrondin.weather.common.dependencyinjection;

import android.content.Context;
import android.view.LayoutInflater;

import androidx.fragment.app.FragmentActivity;

import com.sergeidrondin.weather.networking.WeatherApi;
import com.sergeidrondin.weather.screens.common.ViewMvcFactory;

public class ControllerCompositionRoot {
    private final ActivityCompositionRoot mActivityCompositionRoot;

    public ControllerCompositionRoot(ActivityCompositionRoot activityCompositionRoot) {
        mActivityCompositionRoot = activityCompositionRoot;
    }

    private FragmentActivity getActivity() {
        return mActivityCompositionRoot.getActivity();
    }

    private Context getContext() {
        return getActivity();
    }

    private LayoutInflater getLayoutInflater() {
        return LayoutInflater.from(getContext());
    }

    public WeatherApi getWeatherApi() {
        return mActivityCompositionRoot.getWeatherApi();
    }

    public ViewMvcFactory getViewMvcFactory() {
        return new ViewMvcFactory(getLayoutInflater());
    }
}
