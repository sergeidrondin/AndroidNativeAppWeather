package com.sergeidrondin.weather.screens.common.screensnavigator;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.sergeidrondin.weather.forecast.DailyForecast;
import com.sergeidrondin.weather.screens.common.controllers.FragmentFrameWrapper;
import com.sergeidrondin.weather.screens.forecastdetails.ForecastDetailsFragment;
import com.sergeidrondin.weather.screens.forecastlist.ForecastListFragment;

public class ScreensNavigator {
    private final FragmentManager mFragmentManager;
    private final FragmentFrameWrapper mFragmentFrameWrapper;

    public ScreensNavigator(
            FragmentManager fragmentManager,
            FragmentFrameWrapper fragmentFrameWrapper
    ) {
        mFragmentManager = fragmentManager;
        mFragmentFrameWrapper = fragmentFrameWrapper;
    }

    public void toForecastDetails(DailyForecast forecast) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.addToBackStack(null);
        ft.replace(mFragmentFrameWrapper.getFragmentFrame().getId(), ForecastDetailsFragment.newInstance(forecast)).commit();
    }

    public void toForecastList() {
        mFragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.replace(mFragmentFrameWrapper.getFragmentFrame().getId(), ForecastListFragment.newInstance()).commit();
    }
}
