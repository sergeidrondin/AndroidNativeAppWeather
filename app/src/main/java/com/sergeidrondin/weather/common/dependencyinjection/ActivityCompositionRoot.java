package com.sergeidrondin.weather.common.dependencyinjection;

import androidx.fragment.app.FragmentActivity;

import com.sergeidrondin.weather.networking.WeatherApi;

public class ActivityCompositionRoot {
    private final CompositionRoot mCompositionRoot;
    private final FragmentActivity mActivity;

    public ActivityCompositionRoot(CompositionRoot compositionRoot, FragmentActivity fragmentActivity) {
        this.mCompositionRoot = compositionRoot;
        this.mActivity = fragmentActivity;
    }

    public FragmentActivity getActivity() {
        return mActivity;
    }

    public WeatherApi getWeatherApi() {
        return mCompositionRoot.getWeatherApi();
    }
}
