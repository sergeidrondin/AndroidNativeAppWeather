package com.sergeidrondin.weather.screens.common.screensnavigator;

import com.sergeidrondin.weather.forecast.DailyForecast;
import com.sergeidrondin.weather.screens.common.fragmentframehelper.FragmentFrameHelper;
import com.sergeidrondin.weather.screens.forecastdetails.ForecastDetailsFragment;
import com.sergeidrondin.weather.screens.forecastlist.ForecastListFragment;

public class ScreensNavigator {
    private final FragmentFrameHelper mFragmentFrameHelper;

    public ScreensNavigator(FragmentFrameHelper fragmentFrameHelper) {
        mFragmentFrameHelper = fragmentFrameHelper;
    }

    public void toForecastDetails(DailyForecast forecast) {
        mFragmentFrameHelper.replaceFragment(ForecastDetailsFragment.newInstance(forecast));
    }

    public void toForecastList() {
        mFragmentFrameHelper.replaceFragmentAndClearBackstack(ForecastListFragment.newInstance());
    }

    public void navigateUp() {
        mFragmentFrameHelper.navigateUp();
    }
}
