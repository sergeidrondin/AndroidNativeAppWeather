package com.sergeidrondin.weather.common.dependencyinjection;

import android.content.Context;
import android.view.LayoutInflater;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.sergeidrondin.weather.forecast.FetchOneCallForecastUseCase;
import com.sergeidrondin.weather.networking.WeatherApi;
import com.sergeidrondin.weather.screens.common.controllers.BackPressDispatcher;
import com.sergeidrondin.weather.screens.common.fragmentframehelper.FragmentFrameHelper;
import com.sergeidrondin.weather.screens.common.fragmentframehelper.FragmentFrameWrapper;
import com.sergeidrondin.weather.screens.common.navdrawer.NavDrawerHelper;
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

    private FragmentManager getFragmentManager() {
        return getActivity().getSupportFragmentManager();
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
        return new ViewMvcFactory(getLayoutInflater(), getNavDrawerHelper());
    }

    private NavDrawerHelper getNavDrawerHelper() {
        return (NavDrawerHelper) getActivity();
    }

    public FetchOneCallForecastUseCase getFetchOneCallForecastUseCase() {
        return new FetchOneCallForecastUseCase(getWeatherApi());
    }

    public ScreensNavigator getScreensNavigator() {
        return new ScreensNavigator(getFragmentFrameHelper());
    }

    private FragmentFrameHelper getFragmentFrameHelper() {
        return new FragmentFrameHelper(getActivity(), getFragmentFrameWrapper(), getFragmentManager());
    }

    private FragmentFrameWrapper getFragmentFrameWrapper() {
        return (FragmentFrameWrapper) getActivity();
    }

    public ToastsHelper getToastsHelper() {
        return new ToastsHelper(getContext());
    }

    public ForecastListController getForecastListController() {
        return new ForecastListController(
                getFetchOneCallForecastUseCase(),
                getScreensNavigator(),
                getToastsHelper()
        );
    }

    public ActivityCompositionRoot getActivityCompositionRoot() {
        return mActivityCompositionRoot;
    }

    public BackPressDispatcher getBackPressDispatcher() {
        return (BackPressDispatcher) getActivity();
    }
}
