package com.sergeidrondin.weather.screens.common.screensnavigator;

import android.content.Context;

import com.sergeidrondin.weather.forecast.DailyForecast;
import com.sergeidrondin.weather.screens.forecastdetails.ForecastDetailsActivity;
import com.sergeidrondin.weather.screens.forecastlist.ForecastListActivity;

public class ScreensNavigator {
    private final Context mContext;

    public ScreensNavigator(Context mContext) {
        this.mContext = mContext;
    }

    public void toForecastDetails(DailyForecast forecast) {
        ForecastDetailsActivity.start(mContext, forecast);
    }

    public void toForecastListClearTop() {
        ForecastListActivity.startClearTop(mContext);
    }
}
