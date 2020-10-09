package com.sergeidrondin.weather.common.dependencyinjection;

import android.content.Context;
import android.view.LayoutInflater;

import androidx.fragment.app.FragmentActivity;

import com.sergeidrondin.weather.forecast.FetchOneCallForecastUseCase;
import com.sergeidrondin.weather.networking.WeatherApi;
import com.sergeidrondin.weather.screens.common.toastshelper.ToastsHelper;
import com.sergeidrondin.weather.screens.common.screensnavigator.ScreensNavigator;
import com.sergeidrondin.weather.screens.common.ViewMvcFactory;
import com.sergeidrondin.weather.screens.forecastlist.ForecastListController;

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

    private WeatherApi getWeatherApi() {
        return mActivityCompositionRoot.getWeatherApi();
    }

    public ViewMvcFactory getViewMvcFactory() {
        return new ViewMvcFactory(getLayoutInflater());
    }

    public FetchOneCallForecastUseCase getFetchOneCallForecastUseCase() {
        return new FetchOneCallForecastUseCase(getWeatherApi());
    }

    private ScreensNavigator getScreensNavigator() {
        return new ScreensNavigator(getContext());
    }

    public ToastsHelper getMessagesDisplayer() {
        return new ToastsHelper(getContext());
    }

    public ForecastListController getForecastListController() {
        return new ForecastListController(
                getFetchOneCallForecastUseCase(),
                getScreensNavigator(),
                getMessagesDisplayer()
        );
    }
}
